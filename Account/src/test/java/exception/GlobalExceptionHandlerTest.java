package exception;

import java.net.http.HttpHeaders;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import com.example.Account.dto.ErrorResponseDto;
import com.example.Account.exception.CustomerAllreadyExistException;
import com.example.Account.exception.GlobalExceptionHandler;
import com.example.Account.exception.RescourceNotFoundException;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

	@InjectMocks
	private GlobalExceptionHandler exceptionHandler;
	
	@Mock
    private WebRequest webRequest;
	
	@Mock
	private HttpHeaders headers;

	@Test
	public void testHandleCustomerAllReadyExistException(){
		ErrorResponseDto dto=new ErrorResponseDto();
		dto.setApiPath("/api");
		dto.setErrorCode(HttpStatus.CREATED);
		dto.setErrorMessage("There ara some error");
		dto.setErrorTime(LocalDateTime.now());
		String message="demo";
		CustomerAllreadyExistException allreadyExistException=new CustomerAllreadyExistException(message);
		
		exceptionHandler.handleCustomerAllReadyExistException(allreadyExistException,webRequest);
	}

   @Test
   public void handleRescourceNotFoundException() throws Exception{
	   ErrorResponseDto dto=new ErrorResponseDto();
	   dto.setApiPath("/api");
	   dto.setErrorCode(HttpStatus.ACCEPTED);
	   dto.setErrorMessage("hi");
	   dto.setErrorTime(LocalDateTime.now());
	   RescourceNotFoundException exception=new RescourceNotFoundException("hi", "hellow", "how are you");
	   exceptionHandler.handleRescourceNotFoundException(exception, webRequest); 
   }
   
   
   @Test
   public void handleGlobalRunTimeExceptionTest() {
	   ErrorResponseDto dto=new ErrorResponseDto();
	   dto.setApiPath("/api");
	   dto.setErrorCode(HttpStatus.ACCEPTED);
	   dto.setErrorMessage("hi");
	   dto.setErrorTime(LocalDateTime.now());
	   Exception exception=new Exception();
	   exceptionHandler.handleGlobalRunTimeException(exception,webRequest);	   
   }
   
   @Test
   public void handleMethodArgumentNotValidTest() {
	   MethodArgumentNotValidException argumentNotValidException=new MethodArgumentNotValidException(null, null);
//	   exceptionHandler.handleMethodArgumentNotValid
   }
	
}
