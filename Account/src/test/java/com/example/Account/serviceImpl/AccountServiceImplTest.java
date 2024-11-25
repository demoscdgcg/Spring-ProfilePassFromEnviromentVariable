package com.example.Account.serviceImpl;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Account.Model.Accounts;
import com.example.Account.Model.Customer;
import com.example.Account.accountServiceImpl.AccountServiceImpl;
import com.example.Account.dto.AccountsDto;
import com.example.Account.dto.CustomerDto;
import com.example.Account.dto.Result;
import com.example.Account.mapper.CustomerMapper;
import com.example.Account.repository.AccountsRepo;
import com.example.Account.repository.CustomerRepo;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

	@InjectMocks
	private AccountServiceImpl accountService;

	@Mock
	private CustomerRepo customerRepo;

	@Mock
	private AccountsRepo accountsRepo;

	@Mock
	private CustomerMapper customerMapper;

	@Test
	public void createCustomer() {
		AccountsDto accountsDto=new AccountsDto();
		accountsDto.setAccountNo(457654);
		accountsDto.setAccountType("saving");
		accountsDto.setBranchAddress("balsore");
		CustomerDto customerDto = new CustomerDto();
		customerDto.setEmail("akashbhol9900@gmail.com");
		customerDto.setMobileNumber("8328902083");
		customerDto.setName("Akash");
		customerDto.setAccountsDto(accountsDto);
		
		Customer customer = new Customer();
		Optional<Customer> optionalCustomer = Optional.of(customer);
		Mockito.when(customerRepo.findByMobileNo(customerDto.getMobileNumber())).thenReturn(optionalCustomer);
		Mockito.when(customerRepo.save(Mockito.any())).thenReturn(new Customer());
		Mockito.when(accountsRepo.save(Mockito.any())).thenReturn(new Accounts());
		Result account = accountService.createAccount(customerDto);
		
//		Mockito.when(customerRepo.findByMobileNo(customerDto.getMobileNumber())).thenThrow(new RescourceNotFoundException("The given ", "mobile no", "mobileNumber"));
//		Result account1 = accountService.createAccount(customerDto);
//		
//		Mockito.when(accountsRepo.save(Mockito.any())).thenReturn(null);
//		Result createAccount = accountService.createAccount(null);
		
	}

	@Test
	public void getAccountByMobileNoTest() {
		// Arrange
		Customer customer = new Customer();
		customer.setId(1L);
//        customer.setName("John Doe");
//        customer.setMobileNo("3456788765");
//
		Accounts account = new Accounts();
//        account.setAccountNo(1234567890L); // Set a sample account number
//        account.setAccountType("Savings");
//        account.setCustomerId(customer.getId());

		// Mock repository behaviors
		Mockito.when(customerRepo.findByMobileNo("3456788765")).thenReturn(Optional.of(customer));
		Mockito.when(accountsRepo.findByCustomerId(1L)).thenReturn(Optional.of(account));

		// Act
		Result accountByMobileNo = accountService.getAccountByMobileNo("3456788765");

	}

	@Test
	public void updateCustomerTest() {
		Long id = 4L;
		AccountsDto accountsDto = new AccountsDto();
		accountsDto.setAccountNo(435678976);
		accountsDto.setAccountType("saving");
		accountsDto.setBranchAddress("balsore");
		CustomerDto customerDto = new CustomerDto();
		customerDto.setMobileNumber("34567876543");
		customerDto.setEmail("akashbhol9900@gmail.com");
		customerDto.setName("akah");
		customerDto.setAccountsDto(accountsDto);

		Customer customer = new Customer();
		customer.setMobileNo("34567876543");
		customer.setName("aksh");
		customer.setId(4);
		Accounts accounts = new Accounts();
		accounts.setCustomerId(customer.getId());
		accounts.setAccountType("saving");
		accounts.setBranchAddress("balsore");

		Mockito.when(customerRepo.findById(Mockito.any())).thenReturn(Optional.of((customer)));
		Mockito.when(accountsRepo.findByCustomerId(Mockito.anyLong())).thenReturn(Optional.of(accounts));
		Mockito.when(customerRepo.save(customer)).thenReturn(customer);
//        Mockito.when(customerMapper.mapToCustomerDto(customer,new CustomerDto())).thenReturn(customerDto);
		Mockito.when(accountsRepo.save(Mockito.any())).thenReturn(accounts);
		accountService.updateCustomer(customerDto, id);
	}

	@Test
	public void deleteCustomerTest() {
		long id = 3l;
		Customer customer = new Customer();
		customer.setId(3l);
		customer.setEmail("akashbhol9900@gmail.com");
		customer.setName("akash");
		customer.setMobileNo("4567876567");

		Accounts accounts = new Accounts();
		accounts.setCustomerId(3l);
		accounts.setAccountNo(45678133l);
		accounts.setBranchAddress("bimburia");

		Mockito.when(customerRepo.findById(id)).thenReturn(Optional.of(customer));
		Mockito.when(accountsRepo.findByCustomerId(customer.getId())).thenReturn(Optional.of(accounts));
//        Mockito.when(accountsRepo.deleteById(accounts.getCustomerId()));
		Result result = accountService.deleteCustomer(id);
	}
}
