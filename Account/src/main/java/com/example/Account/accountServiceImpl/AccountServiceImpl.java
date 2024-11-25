package com.example.Account.accountServiceImpl;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Account.IAccountService.AccountService;
import com.example.Account.Model.Accounts;
import com.example.Account.Model.Customer;
import com.example.Account.basicConfig.BsicUtil;
import com.example.Account.constants.AccountsConstants;
import com.example.Account.dto.AccountsDto;
import com.example.Account.dto.CustomerDto;
import com.example.Account.dto.Result;
import com.example.Account.exception.CustomerAllreadyExistException;
import com.example.Account.exception.RescourceNotFoundException;
import com.example.Account.mapper.AccountMapper;
import com.example.Account.mapper.CustomerMapper;
import com.example.Account.repository.AccountsRepo;
import com.example.Account.repository.CustomerRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImpl extends BsicUtil implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountsRepo accountsRepo;

    @Autowired
    private CustomerRepo customerRepo;

    /**
     * @param customerDto
     * @return result
     */
    @Override
    public Result createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> customerExistByMobileNo = customerRepo.findByMobileNo(customerDto.getMobileNumber());
        if (isNullOrEmpty(customerExistByMobileNo)) {
            throw new CustomerAllreadyExistException("Customer Already Exists By Mobile No: " + customerDto.getMobileNumber());
        }
//        customer.setCreatedAt(LocalDateTime.now());
//        customer.setCreatedBy("anonimous");
        Customer save = customerRepo.save(customer);
        if (isNullOrEmpty(save)) {
            return prepareResponseObject("002", "No Data Available", null);
        }
        Accounts save1 = accountsRepo.save(createNewAccounts(customer));
        Map<String, Object> map = new HashMap<>();
        map.put("customerDto", save);
        map.put("account", save1);
        Result result = new Result();
        result.setData(map);
        return prepareResponseObject("003", "Customer Account Created SuccessFully", result);
    }

    /**
     * @param customer
     * @return Accounts
     */
    private Accounts createNewAccounts(Customer customer) {
        Accounts accounts = new Accounts();
        accounts.setCustomerId(customer.getId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        accounts.setAccountNo(randomAccNumber);
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
//        accounts.setCreatedAt(LocalDateTime.now());
//        accounts.setCreatedBy("Anonimous");
        return accounts;
    }

    /**
     * @param mobileNo
     * @return
     */
    @Override
    public Result getAccountByMobileNo(String mobileNo) {
        Customer custmorFindByEmail = customerRepo.findByMobileNo(mobileNo).orElseThrow(
                () -> new RescourceNotFoundException("custmorFindByEmail", "mobileNo", mobileNo)
        );

        Accounts getAccountByCustomerId = accountsRepo.findByCustomerId(custmorFindByEmail.getId()).orElseThrow(
                () -> new RescourceNotFoundException("getAccountByCustomerId", "customerId", Long.toString(custmorFindByEmail.getId()))
        );
        Map<String, Object> map = new HashMap<>();
        map.put("customerDto", CustomerMapper.mapToCustomerDto(custmorFindByEmail, new CustomerDto()));
        map.put("accountDto", AccountMapper.mapToAccountsDto(getAccountByCustomerId, new AccountsDto()));
        return prepareResponseObject("003", "Record fetch by the Given MobilenO", map);
    }

    /**
     * @param customerDto
     * @param id
     * @return Result
     */
    @Override
    public Result updateCustomer(CustomerDto customerDto, long id) {
        Customer findCustomerById = customerRepo.findById(id).orElseThrow(
                () -> new RescourceNotFoundException("findCustomerById", "id", String.valueOf(id))
        );
        long customerId=findCustomerById.getId();
        Accounts accountsDetails = accountsRepo.findByCustomerId(customerId).orElseThrow(
                () -> new RescourceNotFoundException("accounts","customerId",String.valueOf(customerId))
        );

        findCustomerById.setName(customerDto.getName());
        findCustomerById.setEmail(customerDto.getEmail());
        findCustomerById.setMobileNo(customerDto.getMobileNumber());
        findCustomerById.setUpdatedAt(LocalDateTime.now());
        Customer updatedCustomer = customerRepo.save(findCustomerById);
        CustomerDto customerDto2 = CustomerMapper.mapToCustomerDto(updatedCustomer, new CustomerDto());
        accountsDetails.setCustomerId(findCustomerById.getId());
//        accountsDetails.setAccountNo(customerDto.getAccountsDto().getAccountNo());
        accountsDetails.setAccountType(customerDto.getAccountsDto().getAccountType());
        Accounts updatedAccount = accountsRepo.save(accountsDetails);
        AccountsDto accountsDto = AccountMapper.mapToAccountsDto(updatedAccount, new AccountsDto());
        Map<String,Object> map=new HashMap<>();
        map.put("customerDto",customerDto2);
        map.put("accountsDto",accountsDto);
        CustomerDto customerDto1 = CustomerMapper.mapToCustomerDto(updatedCustomer, new CustomerDto());
        return prepareResponseObject("005","Customer Updated Successfully",map);

    }

    @Transactional
    @Modifying
    @Override
    public Result deleteCustomer(long id) {
        Customer customerDetailsByTheId = customerRepo.findById(id).orElseThrow(
                () -> new RescourceNotFoundException("customer details not found by the ", "id", String.valueOf(id))
        );
        Accounts accountDetailsByTheGivenId = accountsRepo.findByCustomerId(id).orElseThrow(
                () -> new RescourceNotFoundException("The Given Id is Not Present", "id", String.valueOf(id))
        );
        accountsRepo.deleteById(accountDetailsByTheGivenId.getId());
        customerRepo.deleteById(id);

        return prepareResponseObject("006","Customer Deleted SuccessFully",null);
    }

}
