package com.CodingNinjas.LeaveXpress.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LeaveNotFoundException extends HttpClientErrorException {

	
	private static final long serialVersionUID = 1L;

	public LeaveNotFoundException(HttpStatus statusCode) {
		super(statusCode);

	}

}
