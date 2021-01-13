package com.vij.football.response;

public class FootballResponse {

  private String message;

  private ErrorResponse error;

  public FootballResponse(String message, ErrorResponse error) {
    super();
    this.message = message;
    this.error = error;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ErrorResponse getError() {
    return error;
  }

  public void setError(ErrorResponse error) {
    this.error = error;
  }
}
