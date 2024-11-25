package com.example.Account.Dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.example.Account.dto.ErrorResponseDto;

@ExtendWith(MockitoExtension.class)
public class ErrorResponceDtoTest {

	@Test
	public void setGetTest() {
		ErrorResponseDto dto=new ErrorResponseDto();
		dto.setApiPath("/api");
		dto.setErrorCode(HttpStatus.CREATED);
		dto.setErrorMessage("This is internal server error");
		dto.setErrorTime(LocalDateTime.now());
		
		ErrorResponseDto dto1=new ErrorResponseDto();
		dto1.setApiPath(dto.getApiPath());
		dto1.setErrorCode(dto.getErrorCode());
		dto1.setErrorMessage(dto.getErrorMessage());
		dto1.setErrorTime(dto.getErrorTime());
	}
	
	@Test
	public void AllArgsConstructor() {
		ErrorResponseDto dto=new ErrorResponseDto("/api",HttpStatus.OK,"ok",LocalDateTime.now());
		 assertNotNull(dto);
		 dto.toString();
	}
	
	@Test
	public void noArgsConstructor() {
		ErrorResponseDto dto=new ErrorResponseDto();
	   assertNotNull(dto);
	}
	
	
}
