package com.nagarro.accountmanagement.mapper;

import com.nagarro.accountmanagement.dto.AccountDto;
import com.nagarro.accountmanagement.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto accountToAccountDto(Account account);

    Account accountDtoToAccount(AccountDto accountDto);
}
