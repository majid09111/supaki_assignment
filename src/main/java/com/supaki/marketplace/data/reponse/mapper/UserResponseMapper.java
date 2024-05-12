package com.supaki.marketplace.data.reponse.mapper;

import com.supaki.marketplace.data.entity.User;
import com.supaki.marketplace.data.exception.ApiException;
import com.supaki.marketplace.data.reponse.UserResponse;
import com.supaki.marketplace.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("userResponseMapper")
public class UserResponseMapper implements Mapper<User, UserResponse> {

    @Autowired
    private ProductService productService;

    @Override
    public User toEntity(UserResponse response){return null;}

    @Override
    public UserResponse toPojo(User user) throws ApiException {
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .totalAmount(user.getTotalAmount())
                .productsOwned(productService.listUsersProducts(user.getId()))
                .build();

        return userResponse;
    }

}
