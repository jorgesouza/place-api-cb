package com.clickbus.place.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PlaceExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String message = messageSource.getMessage("message.invalid", null, LocaleContextHolder.getLocale());
		String exception = ex.getCause().toString();
		List<Error> errors = Arrays.asList(new Error(message, exception));
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Error> errors = createListError(ex.getBindingResult());
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		String message = messageSource.getMessage("message.not-found", null, LocaleContextHolder.getLocale());
		String exception = ex.toString();
		List<Error> errors = Arrays.asList(new Error(message, exception));
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	private List<Error> createListError(BindingResult bindingResult){
		List<Error> errors = new ArrayList<>();
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {			
			String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String exception = fieldError.toString();
			errors.add(new Error(message, exception));
		}
		return errors;
	}

	public static class Error {

		private String message;
		private String exception;

		public Error(String message, String exception) {
			this.message = message;
			this.exception = exception;
		}

		public String getMessage() {
			return message;
		}

		public String getException() {
			return exception;
		}
	}
}
