package com.supaki.marketplace.controller;

import com.supaki.marketplace.data.reponse.BaseResponse;
import com.supaki.marketplace.data.reponse.ListProducts;
import com.supaki.marketplace.data.reponse.ProductResponse;
import com.supaki.marketplace.data.reponse.ResponseUtil;
import com.supaki.marketplace.data.request.ProductRequest;
import com.supaki.marketplace.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Component
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/list", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<ProductResponse>> createProduct(@RequestBody @Valid ProductRequest productRequest ,@RequestParam("userId")long userId){
        ResponseUtil<ProductResponse> responseUtil = new ResponseUtil<>();
        return responseUtil.getResponse(()->productService.createProduct(productRequest,userId));
    }

    @RequestMapping(value = "/fetch-all", method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<ListProducts>> listAllProducts(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        ResponseUtil<ListProducts> responseUtil= new ResponseUtil<>();
        return responseUtil.getResponse(()-> productService.listProducts(pageSize,pageNumber));
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<String>> buyProduct(@RequestParam("productId")long productId, @RequestParam("buyerId")long buyerId){
        ResponseUtil<String> responseUtil = new ResponseUtil<>();
        return responseUtil.getResponse(()-> productService.purchaseProduct(productId, buyerId));
    }

}
