package com.nagarro.accountmanagement.controller;

import com.nagarro.accountmanagement.dto.AccountDto;
import com.nagarro.accountmanagement.dto.MoneyRequestDto;
import com.nagarro.accountmanagement.entity.Account;
import com.nagarro.accountmanagement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;


    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody Account account) {
        return ResponseEntity.ok().body(accountService.addAccount(account));
    }

    @PutMapping("/add-money/{accountId}")
    public ResponseEntity<String> addMoneyToAccount(@PathVariable Long accountId, @RequestBody MoneyRequestDto moneyRequestDto) {
        accountService.addMoneyToAccount(accountId, moneyRequestDto.getCustomerId(), moneyRequestDto.getAmount());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully Added");
    }

    @PutMapping("/withdraw-money/{accountId}")
    public ResponseEntity<String> withdrawMoneyToAccount(@PathVariable Long accountId, @RequestBody MoneyRequestDto moneyRequestDto) {
        accountService.withdrawMoneyToAccount(accountId, moneyRequestDto.getCustomerId(), moneyRequestDto.getAmount());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully Withdraw");
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> addAccount(@PathVariable Long accountId) {
        return ResponseEntity.ok().body(accountService.getAccountById(accountId));
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.ok().body("Successfully Deleted");
    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<String> deleteAccountByCustomerId(@PathVariable Long customerId) {
        accountService.deleteAccountByCustomerId(customerId);
        return ResponseEntity.ok().body("Successfully Deleted");
    }

}
