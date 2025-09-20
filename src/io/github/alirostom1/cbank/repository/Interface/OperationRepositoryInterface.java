package io.github.alirostom1.cbank.repository.Interface;

import io.github.alirostom1.cbank.model.entity.Operation;
import java.sql.SQLException;
import java.util.List;

public interface OperationRepositoryInterface{
    boolean store(Operation op) throws SQLException; 
    List<Operation> getAll(String code) throws SQLException;
} 
