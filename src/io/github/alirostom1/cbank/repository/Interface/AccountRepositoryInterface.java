package io.github.alirostom1.cbank.repository.Interface;

import io.github.alirostom1.cbank.model.entity.Account; 
import java.util.Optional;
import java.sql.SQLException;

public interface AccountRepositoryInterface{
    boolean store(Account account) throws SQLException;
    boolean save(Account account) throws SQLException;
    Optional<Account> findByCode(String code) throws SQLException;
}
