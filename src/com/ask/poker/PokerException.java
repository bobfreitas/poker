package com.ask.poker;

public class PokerException extends Exception {

	private static final long serialVersionUID = -3848930914496402391L;

	public PokerException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public PokerException(String arg0) {
		super(arg0);
	}

}
