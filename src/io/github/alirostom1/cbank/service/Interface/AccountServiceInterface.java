package io.github.alirostom1.cbank.service.Interface;

import java.util.Optional;

public interface AccountServiceInterface{
    Optional<String> createAccount(String type);
}
