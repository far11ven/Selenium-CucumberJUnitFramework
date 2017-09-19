package com.utilities;

public class TestFailException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TestFailException(String s)
	{
		// Call constructor of parent Exception
		super(s);
	}

}
