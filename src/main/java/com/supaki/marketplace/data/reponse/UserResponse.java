package com.supaki.marketplace.data.reponse;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserResponse {

    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private long monthlyQuota;
    private long totalAmount;
    private List<UserAccountDetailResponse> productsOwned;
}
