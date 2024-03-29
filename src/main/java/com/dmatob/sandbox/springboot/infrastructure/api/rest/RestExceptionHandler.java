package com.dmatob.sandbox.springboot.infrastructure.api.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dmatob.sandbox.springboot.application.exception.ArticleNotFoundException;
import com.dmatob.sandbox.springboot.application.exception.ArticleTypeNotFoundException;
import com.dmatob.sandbox.springboot.application.exception.DuplicatedArticleException;
import com.dmatob.sandbox.springboot.domain.exception.InvalidArticlePriceException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { ArticleNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected ResponseEntity<Object> articleNotFound(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "El artículo especificado no existe";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(value = { InvalidArticlePriceException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> invalidArticlePrice(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "El precio del artículo es incorrecto. El precio ha de ser mayor a 0€";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = { ArticleTypeNotFoundException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> invalidArticleTypeException (RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "El tipo de artículo indicado no existe";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = { DuplicatedArticleException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> duplicatedArtcile(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "El articulo ya existe";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@Override
	@Nullable
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {

			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}