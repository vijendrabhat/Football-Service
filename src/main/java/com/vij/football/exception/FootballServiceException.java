package com.vij.football.exception;

public class FootballServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	private String userMessage = "";

	private String userStackTrace = "";

	public FootballServiceException() {
		super();
	}

	public FootballServiceException(String message) {
		super(message);
	}

	public FootballServiceException(Throwable throwable) {
		super();
	}

	public FootballServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public FootballServiceException(String userMessage, String message, String userStackTrace) {
		    super(message);
		    this.setUserMessage(userMessage);
		    this.setUserStackTrace(userStackTrace);
		  }

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getUserStackTrace() {
		return userStackTrace;
	}

	public void setUserStackTrace(String userStackTrace) {
		this.userStackTrace = userStackTrace;
	}
}
