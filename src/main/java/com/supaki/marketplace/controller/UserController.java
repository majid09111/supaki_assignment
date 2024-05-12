package com.supaki.marketplace.controller;

import com.supaki.marketplace.data.reponse.BaseResponse;
import com.supaki.marketplace.data.reponse.ResponseUtil;
import com.supaki.marketplace.data.reponse.UserResponse;
import com.supaki.marketplace.data.request.CreateUserRequest;
import com.supaki.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Component
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<UserResponse>> createUser(@RequestBody @Valid CreateUserRequest registerUser){
        ResponseUtil<UserResponse> responseUtil = new ResponseUtil<>();
        return responseUtil.getResponse(()-> userService.createUser(registerUser));
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<UserResponse>> profile(@RequestParam("userId")long userId){
        ResponseUtil<UserResponse> responseUtil = new ResponseUtil<>();
        return responseUtil.getResponse(()-> userService.profile(userId));
    }



}
