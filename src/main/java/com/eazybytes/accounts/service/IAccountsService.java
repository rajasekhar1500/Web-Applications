package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * @param customerDto - Customer Object
     */
    void createAccount(CustomerDto customerDto);
}
