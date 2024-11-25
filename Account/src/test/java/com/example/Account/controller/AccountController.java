package com.example.Account.controller;

//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.Account.IAccountService.AccountService;
import com.example.Account.dto.AccountsDto;
import com.example.Account.dto.CustomerDto;
import com.example.Account.dto.Result;

//@RunWith(MockitoExtension.class)
@ExtendWith(MockitoExtension.class)
public class AccountController {

    @InjectMocks
    private AccountsController accountsController;

    @Mock
    private AccountService accountService;

    @Test
    public void createCustomer(){
        CustomerDto customerDto=new CustomerDto();
        customerDto.setMobileNumber("8328902087");
        customerDto.setName("akash");
        customerDto.setEmail("akashbhol9900@gmail.com");
        Mockito.when(accountService.createAccount(Mockito.any())).thenReturn(new Result());
        accountsController.createCustomer(new CustomerDto());
    }

    @Test
    public void getAccountByMobileNo(){
        String mobileNo="8328902087";
        Mockito.when(accountService.getAccountByMobileNo(Mockito.any())).thenReturn(new Result());
        accountsController.getAccountByMobileNo(mobileNo);
    }

    @Test
    public void updateCustomer(){
        CustomerDto customerDto=new CustomerDto();
        customerDto.setMobileNumber("8328902087");
        customerDto.setName("akash");
        customerDto.setEmail("akashbhol9900@gmail.com");
        AccountsDto accountsDto=new AccountsDto();
        accountsDto.setAccountNo(354656543);
        accountsDto.setAccountType("saving");
        accountsDto.setBranchAddress("sdfgds");
        long id=3;
        Mockito.when(accountService.updateCustomer(Mockito.any(),Mockito.anyLong())).thenReturn(new Result());
        accountsController.updateCustomer(customerDto,id);
    }

    @Test
    public void deleteCustomer(){
        long id=9;
        Mockito.when(accountService.deleteCustomer(Mockito.anyLong())).thenReturn(new Result());
        Result result = accountsController.deleteCustomer(id);
    }
}
