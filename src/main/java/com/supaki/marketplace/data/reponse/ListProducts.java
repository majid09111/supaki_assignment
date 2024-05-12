package com.supaki.marketplace.data.reponse;

import lombok.Data;

import java.util.List;

@Data
public class ListProducts {

    List<ProductResponse> data;
    long count;

}
