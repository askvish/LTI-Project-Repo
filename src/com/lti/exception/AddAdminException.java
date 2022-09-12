package com.lti.exception;

public class AddAdminException extends Exception {

  private String message;

  public AddAdminException(String message) {
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }
}
