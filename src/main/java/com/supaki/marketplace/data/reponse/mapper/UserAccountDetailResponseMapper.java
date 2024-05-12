package com.supaki.marketplace.data.reponse.mapper;

import com.supaki.marketplace.data.entity.UserAccount;
import com.supaki.marketplace.data.reponse.UserAccountDetailResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("userAccountDetailResponseMapper")
public class UserAccountDetailResponseMapper implements Mapper<UserAccount, UserAccountDetailResponse> {

    @Override
    public UserAccount toEntity(UserAccountDetailResponse response){
        return null;
    }

    @Override
    public UserAccountDetailResponse toPojo(UserAccount userAccount){
        UserAccountDetailResponse response = UserAccountDetailResponse.builder()
                .id(userAccount.getId())
                .userId(userAccount.getUserId())
                .productType(userAccount.getProductType())
                .productName(userAccount.getProductName())
                .quantity(userAccount.getQuantity())
                .build();

        return response;
    }

}
