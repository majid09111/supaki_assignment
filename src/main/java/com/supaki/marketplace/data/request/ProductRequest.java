package com.supaki.marketplace.data.request;

import com.supaki.marketplace.data.constants.Constants;
import com.supaki.marketplace.data.enums.ProductType;
import lombok.Data;
import org.apache.tomcat.util.bcel.Const;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class ProductRequest {

    private ProductType productType;
    private String name;

    @Min(value=1,message = Constants.QUANTITY_ERROR)
    private long quantity;
    
    @Min(value=10,message = Constants.PRICE_ERROR)
    @Max(value = 1000, message = Constants.PRICE_ERROR)
    private long price;
}
