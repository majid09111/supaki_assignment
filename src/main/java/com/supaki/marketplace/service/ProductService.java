package com.supaki.marketplace.service;

import com.supaki.marketplace.data.constants.Constants;
import com.supaki.marketplace.data.entity.Product;
import com.supaki.marketplace.data.entity.Transaction;
import com.supaki.marketplace.data.entity.User;
import com.supaki.marketplace.data.entity.UserAccount;
import com.supaki.marketplace.data.exception.ApiException;
import com.supaki.marketplace.data.reponse.ListProducts;
import com.supaki.marketplace.data.reponse.ProductResponse;
import com.supaki.marketplace.data.reponse.UserAccountDetailResponse;
import com.supaki.marketplace.data.reponse.mapper.ProductResponseMapper;
import com.supaki.marketplace.data.reponse.mapper.UserAccountDetailResponseMapper;
import com.supaki.marketplace.data.repository.ProductRepository;
import com.supaki.marketplace.data.repository.TransactionRepository;
import com.supaki.marketplace.data.repository.UserAccountRepository;
import com.supaki.marketplace.data.repository.UserRepository;
import com.supaki.marketplace.data.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class ProductService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProductResponseMapper productResponseMapper;

    @Autowired
    private UserAccountDetailResponseMapper userAccountDetailResponseMapper;

    public ProductResponse createProduct(ProductRequest productRequest, long userId) throws ApiException {
         User user = userRepository.findById(userId).orElseThrow(()-> new ApiException(Constants.USER_NOT_FOUND, HttpStatus.BAD_REQUEST));
         //Check user's last selling date
         if(user.getBuyDate()!=null) {
             checkIfListingIsPossible(user.getBuyDate());
         }


         Product product = new Product();
         product.setProductType(productRequest.getProductType());
         product.setName(productRequest.getName());
         product.setPrice(productRequest.getPrice());
         product.setQuantity(productRequest.getQuantity());
         product.setUserId(userId);

         productRepository.save(product);

        return productResponseMapper.toPojo(product);

    }

    public ListProducts listProducts(int pageSize, int pageNumber) throws ApiException {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        List<Product> productList = productRepository.findAllProducts(pageable).getContent();
        long count = productRepository.count();

        ListProducts response = new ListProducts();
        response.setData(productResponseMapper.toPojos(productList));
        response.setCount(count);

        return response;
    }

    public String purchaseProduct(long productId, long buyerId) throws ApiException {
        User buyer = userRepository.findById(buyerId).get();
        if(buyer.getBuyDate()!=null){
            checkIfBuyingIsPossible(buyer.getBuyDate());
        }
        Product product = productRepository.findById(productId).get();
        User seller = userRepository.findById(product.getUserId()).get();

        //Check if buyer is re-buying the same product again then increase the count in the inventory
        Optional<UserAccount> optionalPreOwnedProduct = userAccountRepository.findByUserIdAndProductName(buyerId,product.getName());
        if(optionalPreOwnedProduct.isPresent()){
            UserAccount preOwnedProduct = optionalPreOwnedProduct.get();
            preOwnedProduct.setQuantity((preOwnedProduct.getQuantity()+1));

            userAccountRepository.save(preOwnedProduct);

        }else{
            UserAccount newProduct = new UserAccount();
            newProduct.setUserId(buyerId);
            newProduct.setProductType(product.getProductType());
            newProduct.setProductName(product.getName());
            newProduct.setQuantity(1);

            userAccountRepository.save(newProduct);
        }

        product.setQuantity((product.getQuantity()-1));

        seller.setSellDate(new Timestamp(System.currentTimeMillis()));
        buyer.setBuyDate(new Timestamp(System.currentTimeMillis()));

        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID().toString());
        transaction.setBuyerId(buyerId);
        transaction.setSellerId(seller.getId());
        transaction.setTotalAmount((double)product.getPrice());
        double commission = product.getPrice()*0.1;
        double sellerAmount = (product.getPrice()*1.0)-commission;
        transaction.setSellerAmount(sellerAmount);
        transaction.setCommissionAmount(commission);
        seller.setTotalAmount((long) (seller.getTotalAmount()+sellerAmount));

        productRepository.save(product);
        userRepository.save(seller);
        userRepository.save(buyer);
        transactionRepository.save(transaction);

        return "Transaction Successful";


    }

    public List<UserAccountDetailResponse> listUsersProducts(long userId) throws ApiException {
        List<UserAccount> userAccountDetails = userAccountRepository.findAllByUserId(userId);
        return userAccountDetailResponseMapper.toPojos(userAccountDetails);
    }

    public void checkIfListingIsPossible(Date sellingDate) throws ApiException {
        Date currentTimeStamp = Calendar.getInstance().getTime();
        long difference = currentTimeStamp.getTime()-sellingDate.getTime();
        long minutes = TimeUnit.MILLISECONDS.toMinutes(difference);
        if(minutes<1440){
            throw new ApiException(Constants.USER_CANNOT_SELL, HttpStatus.BAD_REQUEST);
        }
    }

    public void checkIfBuyingIsPossible(Date buyDate) throws ApiException {
        Date currentTimeStamp = Calendar.getInstance().getTime();
        long difference = currentTimeStamp.getTime()-buyDate.getTime();
        long minutes = TimeUnit.MILLISECONDS.toMinutes(difference);
        if(minutes<43200){
            throw new ApiException(Constants.QUOTA_ERROR, HttpStatus.BAD_REQUEST);
        }
    }

}
