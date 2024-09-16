package com.nagarro.accountmanagement.service;

import com.nagarro.accountmanagement.dto.AccountDto;
import com.nagarro.accountmanagement.entity.Account;

public interface AccountService {
    AccountDto addAccount(Account account);
    void addMoneyToAccount(Long accountId, Long customerId, double amount);
    void withdrawMoneyToAccount(Long accountId, Long customerId, double amount);
    AccountDto getAccountById(Long accountId);

    void deleteAccount(Long accountId);

    void deleteAccountByCustomerId(Long customerId);
}
