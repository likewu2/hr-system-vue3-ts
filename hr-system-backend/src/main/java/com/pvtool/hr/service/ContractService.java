package com.pvtool.hr.service;

import com.pvtool.hr.model.Contract;

public interface ContractService {
    Contract save(Contract contract);
    Contract findById(Long id);
}