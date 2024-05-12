package com.supaki.marketplace.data.repository;

import com.supaki.marketplace.data.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findById(long id);

}
