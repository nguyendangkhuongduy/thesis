package com.duy.thesisManagement.thesis.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
		private static final int httpCode = 400;

		public BadRequestException(String message) {
				super(message);
		}

		public int getHttpStatus() {
				return httpCode;
		}
}
