package com.supaki.marketplace.data.reponse;

import com.supaki.marketplace.data.enums.ProductType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private long id;
    private ProductType productType;
    private String name;
    private long userId;
    private long price;
    private long quantity;

}
