package com.nagarro.accountmanagement.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.accountmanagement.dto.AccountDto;
import com.nagarro.accountmanagement.entity.Account;
import com.nagarro.accountmanagement.exceptions.AccountNotFoundException;
import com.nagarro.accountmanagement.exceptions.BalanceInsufficientException;
import com.nagarro.accountmanagement.exceptions.CustomerIdMismatchException;
import com.nagarro.accountmanagement.exceptions.CustomerNotFoundException;
import com.nagarro.accountmanagement.mapper.AccountMapper;
import com.nagarro.accountmanagement.repository.AccountRepository;
import com.nagarro.accountmanagement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountMapper accountMapper;
    @Autowired
    ObjectMapper objectMapper;

    public void addMoneyToAccount(Long accountId, Long customerId, double amount) {
        Long customerIdFromAccount = getCustomerIdByAccountId(accountId);
        if (!customerIdFromAccount.equals(customerId)) {
            throw new CustomerIdMismatchException("CustomerId not Matched");
        }
        Account account = accountMapper.accountDtoToAccount(getAccountById(accountId));
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    public void withdrawMoneyToAccount(Long accountId, Long customerId, double amount) {
        Long customerIdFromAccount = getCustomerIdByAccountId(accountId);
        if (!customerIdFromAccount.equals(customerId)) {
            throw new CustomerIdMismatchException("CustomerId not Matched");
        }
        Account account = accountMapper.accountDtoToAccount(getAccountById(accountId));
        if (account.getBalance() < amount) {
            throw new BalanceInsufficientException("Insufficient Balance");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }




    public void deleteAccount(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            accountRepository.deleteById(accountId);
        } else {
            throw new AccountNotFoundException("No Account exist with given account Id");
        }
    }

    public void deleteAccountByCustomerId(Long customerId) {
        accountRepository.deleteByCustomerId(customerId);
    }

    private Long getCustomerIdByAccountId(Long accountId) {
        return accountRepository.findCustomerIdByAccountId(accountId);
    }

    public AccountDto getAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            return accountMapper.accountToAccountDto(account.get());
        } else {
            throw new AccountNotFoundException("No Account exist with given account Id");
        }
    }

    public AccountDto addAccount(Account account) {
        return accountMapper.accountToAccountDto(accountRepository.save(account));
    }

}
