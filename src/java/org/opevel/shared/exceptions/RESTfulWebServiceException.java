package org.opevel.shared.exceptions;

import com.google.gwt.user.client.rpc.IsSerializable;

public class RESTfulWebServiceException extends Exception  implements IsSerializable{
	private static final long serialVersionUID = 1L;
	private String message;

	public RESTfulWebServiceException() {
	}

	public RESTfulWebServiceException(String message) {
		super(message);
		this.message = message;
	}

	public RESTfulWebServiceException(Throwable cause) {
		super(cause);
	}

	public RESTfulWebServiceException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
