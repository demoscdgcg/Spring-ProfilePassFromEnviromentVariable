package com.example.Account.Dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Account.dto.Result;

@ExtendWith(MockitoExtension.class)
public class REsultTest {

	@Test
	void testGetterAndSetter() {
		Result result=new Result();
		result.setCode("hdg");
		result.setData(new Object());
		result.setMessage("hi");
		Result result2=new Result();
		result2.setCode(result.getCode());
		result2.setData(result.getData());
		result2.setMessage(result.getMessage());
	}
	
	@Test
	void testAllArgsConstructor() {
		Result result=new Result("hbc",null,"hg");
	}
	
	@Test
	void allArgsConstructor() {
		Result result=new Result();
		result.toString();
	}
}
