package com.m2r.flatfile.exception;


public class FlatFileException extends Exception {

	private static final long serialVersionUID = 1L;

	public FlatFileException(String msg) {
		super(msg);
	}

	public FlatFileException(Throwable cause) {
		super(cause);
	}

}
