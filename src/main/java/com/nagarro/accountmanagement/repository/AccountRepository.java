package com.nagarro.accountmanagement.repository;

import com.nagarro.accountmanagement.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT customerId FROM Account WHERE accountId = ?1")
    Long findCustomerIdByAccountId(Long accountId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Account a WHERE a.customerId = ?1")
    void deleteByCustomerId(Long customerId);

    @Query("FROM Account WHERE customerId = ?1")
    List<Account> findAccountByCustomerId(Long customerId);

}
