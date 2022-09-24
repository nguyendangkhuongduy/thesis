package com.duy.thesisManagement.thesis.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

		private static final int httpStatus = 404;

		public ResourceNotFoundException(String message) {
				super(message);
		}

		public int getHttpStatus() {
				return httpStatus;
		}
}
