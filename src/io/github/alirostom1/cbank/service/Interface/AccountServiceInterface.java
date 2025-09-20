package io.github.alirostom1.cbank.service.Interface;

import java.util.Optional;

import io.github.alirostom1.cbank.model.entity.Account;

public interface AccountServiceInterface{
    Optional<String> createAccount(String type);
    Optional<Account> getAccountByCode(String code);
}
