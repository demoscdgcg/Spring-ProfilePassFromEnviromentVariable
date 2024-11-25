package com.example.Account.Dto;

import com.example.Account.dto.AccountsDto;
import com.example.Account.dto.CustomerDto;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class AccountsDtoTest {

    @Test
    public void setUp(){
        CustomerDto customerDto=new CustomerDto();
        customerDto.setEmail("akashbhol9900@gmail.com");
        customerDto.setName("akash");
        customerDto.setMobileNumber("832890287");
        CustomerDto customerDto1 = new CustomerDto();

        customerDto1.setMobileNumber(customerDto.getMobileNumber());
        customerDto1.setName(customerDto.getMobileNumber());
        customerDto1.setEmail(customerDto.getMobileNumber());
        assertEquals("akashbhol9900@gmail.com",customerDto.getEmail());
        assertEquals("akash",customerDto.getName());
        assertEquals("832890287",customerDto.getMobileNumber());
        assertNotNull(customerDto);
    }
    @Test
    public void noArgsConstrucrorTest(){
        CustomerDto customerDto=new CustomerDto();
//        assertEquals(customerDto,null);
        assertNotNull(customerDto);
    }

    @Test
    public  void allArgsConstructor(){
        CustomerDto customerDto=new CustomerDto("7375753","akash","akasjbhol9900@gmail.com",null);
    }
    
    
    @Test
    public void allArgsConstructorCustomer() {
    	AccountsDto accountsDto=new AccountsDto();
    	accountsDto.setAccountNo(34555);
    	accountsDto.setAccountType("saving");
    	accountsDto.setBranchAddress("schd");
    }
}
