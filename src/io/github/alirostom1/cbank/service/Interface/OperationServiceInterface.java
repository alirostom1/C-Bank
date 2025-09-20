package io.github.alirostom1.cbank.service.Interface;

import io.github.alirostom1.cbank.model.entity.Operation;
import java.util.List;

public interface OperationServiceInterface{
    boolean depositOrWithdraw(Operation op) throws Exception;
    boolean Transfer(String wcode,String dcode,double amount) throws Exception;
    List<Operation> getAll(String code) throws Exception;
}
