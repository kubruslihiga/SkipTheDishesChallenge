package br.challenge.skipthedishes.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.challenge.skipthedishes.exception.SkiptheDishesException;

@ControllerAdvice
public class SkiptheDishesExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseBody
	@ExceptionHandler(SkiptheDishesException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse exceptionHandler(SkiptheDishesException e) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(e.getMessage());
		return errorResponse;
	}
	
}
