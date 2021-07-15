package com.dennis.gocity.exception;

public class InvalidDataException extends Exception {
  private static final long serialVersionUID = 1L;

  public InvalidDataException(String msg) {
    super(msg);
  }

  public InvalidDataException(String msg, Exception e) {
    super(msg + " because of " + e.toString());
  }
}
