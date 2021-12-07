package com.iuh.SpringBootJWT.repository;
import com.iuh.SpringBootJWT.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    Optional<Account> findByUsername(String username);
    Boolean existsByUsername(String username);
}
