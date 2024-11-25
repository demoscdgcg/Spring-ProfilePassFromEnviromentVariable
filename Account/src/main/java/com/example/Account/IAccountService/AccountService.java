package com.example.Account.IAccountService;

import com.example.Account.dto.AccountsDto;
import com.example.Account.dto.CustomerDto;
import com.example.Account.dto.Result;

public interface AccountService {

    /**
     *
     * @param customerDto
     * @return Result
     */
    public Result createAccount(CustomerDto customerDto);

    /**
     * 
     * @param mobileNo
     * @return Result
     */
    public Result getAccountByMobileNo(String mobileNo);

    /**
     *
     * @param customerDto
     * @param id
     * @return Result
     */
   public Result updateCustomer(CustomerDto customerDto,long id);

    /**
     *
     * @param id
     * @return
     */
   public Result deleteCustomer(long id);
}
