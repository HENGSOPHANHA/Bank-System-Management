package com.ams.theamsbanksystem.repository;

import com.ams.theamsbanksystem.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // By extending JpaRepository, we inherit default methods for CRUD operations
    // Additional custom query methods can be added if needed
}
