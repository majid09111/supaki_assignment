package com.supaki.marketplace.service;

import com.supaki.marketplace.data.constants.Constants;
import com.supaki.marketplace.data.entity.User;
import com.supaki.marketplace.data.exception.ApiException;
import com.supaki.marketplace.data.reponse.UserResponse;
import com.supaki.marketplace.data.reponse.mapper.UserResponseMapper;
import com.supaki.marketplace.data.repository.UserRepository;
import com.supaki.marketplace.data.request.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserResponseMapper userResponseMapper;

    public UserResponse createUser(CreateUserRequest createUserRequest) throws ApiException {

        Optional<User> optionalUser = userRepository.findByEmail(createUserRequest.getEmail());
        if(optionalUser.isPresent()){
            throw new ApiException(Constants.USER_EXISTS, HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setEmail(createUserRequest.getEmail());
        user.setFirstName(createUserRequest.getFirstName());
        user.setLastName(createUserRequest.getLastName());
        userRepository.save(user);

        return userResponseMapper.toPojo(user);

    }

    public UserResponse profile(long userId) throws ApiException {
        User user = userRepository.findById(userId).orElseThrow(()->new ApiException(Constants.USER_NOT_FOUND, HttpStatus.BAD_REQUEST));
        return userResponseMapper.toPojo(user);
    }

}
