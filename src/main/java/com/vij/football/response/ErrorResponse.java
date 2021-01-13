package com.vij.football.response;

public class ErrorResponse {

  private int errorCode;

  private String errorMessage;

  private String stackTrace;

  public ErrorResponse(int errorCode, String errorMessage, String stackTrace) {
    super();
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.stackTrace = stackTrace;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getStackTrace() {
    return stackTrace;
  }

  public void setStackTrace(String stackTrace) {
    this.stackTrace = stackTrace;
  }
}
