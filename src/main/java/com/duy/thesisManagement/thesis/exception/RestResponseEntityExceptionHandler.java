package com.duy.thesisManagement.thesis.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler
		extends ResponseEntityExceptionHandler {

		@ExceptionHandler(value = { ResourceNotFoundException.class})
		protected ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex) {
				String bodyOfResponse = "Resource not found: ";
				log.error(bodyOfResponse, ex);
				return ResponseEntity.status(ex.getHttpStatus()).body(bodyOfResponse + ex.getMessage());
		}

		@ExceptionHandler(value = { BadRequestException.class})
		protected ResponseEntity<Object> handleBadRequest(BadRequestException ex) {
				String bodyOfResponse = "Bad request: ";
				log.error(bodyOfResponse, ex);
				return ResponseEntity.status(ex.getHttpStatus()).body(bodyOfResponse + ex.getMessage());
		}

		@ExceptionHandler(value = { Exception.class})
		protected ResponseEntity<Object> handleGeneralException(Exception ex) {
				String bodyOfResponse = "Internal exception happens, please contact admin for more detail!";
				log.error(bodyOfResponse, ex);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(bodyOfResponse);
		}
}
