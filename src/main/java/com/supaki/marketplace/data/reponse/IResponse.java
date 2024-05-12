package com.supaki.marketplace.data.reponse;

public interface IResponse<T> {

    T exec() throws Exception;

}
