package com.supaki.marketplace.data.reponse;

import com.supaki.marketplace.data.enums.ProductType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAccountDetailResponse {

    private long id;
    private long userId;
    private ProductType productType;
    private String productName;
    private long quantity;

}
