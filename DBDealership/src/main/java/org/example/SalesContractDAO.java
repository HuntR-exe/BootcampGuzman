package org.example;

import org.example.SalesContract;

public class SalesContractDAO implements ContractDAO {
    @Override
    public void addContract(Contract contract) {
        saveContract(contract, "sales_contracts");
    }
}