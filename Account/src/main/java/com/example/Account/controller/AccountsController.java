package com.example.Account.controller;

import com.example.Account.IAccountService.AccountService;
import com.example.Account.dto.AccountsContactInfoDto;
import com.example.Account.dto.CustomerDto;
import com.example.Account.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = (MediaType.APPLICATION_JSON_VALUE))
@Tag(name = "Accounts", description = "APIs for managing customer accounts.")
public class AccountsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountsController.class);

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;

    @Autowired
    private AccountService accountService;

    //url->http://localhost:8080/api/create/customer
    @Operation(
            summary = "Create a new customer",
            description = "This endpoint allows you to create a new customer by providing necessary details in the request body.",
            tags = {"Customer"}
    )
    @PostMapping("/create/customer")
    public Result createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        LOGGER.info("The Dto value:{}", customerDto);
        Result account = accountService.createAccount(customerDto);
        return account;
    }

    //url->http://localhost:8080/api/get/account
    @GetMapping("/get/account")
    public Result getAccountByMobileNo(@RequestParam("mobileNo")
                                       @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits")
                                       String mobileNo) {
        Result accountByMobileNo = accountService.getAccountByMobileNo(mobileNo);
        return accountByMobileNo;
    }

    @PutMapping("/update/customer")
    public Result updateCustomer(@Valid @RequestBody CustomerDto customerDto, @RequestParam("id") long id) {
        return accountService.updateCustomer(customerDto, id);
    }

    @DeleteMapping("/delete/customer")
    public Result deleteCustomer(@RequestParam("id") long id) {
        return accountService.deleteCustomer(id);
    }


//    ==================================================================
    //that two api for getting the date from application.yml
    //and that application.yml was present with different enviromet

//    =======================================================================
    @GetMapping("/get/version")
    public ResponseEntity<String> getVersionFromApplicatioYml(){
        return new ResponseEntity<>(buildVersion, HttpStatus.OK);
    }

    @GetMapping("/get/version/accounts")
    public ResponseEntity<?> getMultipleDateApplicatioYml(){
        return new ResponseEntity<>(accountsContactInfoDto,HttpStatus.OK);
    }

}
