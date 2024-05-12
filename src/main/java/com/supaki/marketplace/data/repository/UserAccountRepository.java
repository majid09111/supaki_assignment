package com.supaki.marketplace.data.repository;

import com.supaki.marketplace.data.entity.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

    Optional<UserAccount> findByUserIdAndProductName(long userId, String productName);

    List<UserAccount> findAllByUserId(long userId);
}
