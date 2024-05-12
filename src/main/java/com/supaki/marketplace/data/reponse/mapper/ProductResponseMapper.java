package com.supaki.marketplace.data.reponse.mapper;

import com.supaki.marketplace.data.entity.Product;
import com.supaki.marketplace.data.reponse.ProductResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("productResponseMapper")
public class ProductResponseMapper implements Mapper<Product, ProductResponse> {

    @Override
    public Product toEntity(ProductResponse productResponse){
        return null;
    }

    @Override
    public ProductResponse toPojo(Product product){
        ProductResponse response = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .productType(product.getProductType())
                .userId(product.getUserId())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();

        return response;
    }

}
