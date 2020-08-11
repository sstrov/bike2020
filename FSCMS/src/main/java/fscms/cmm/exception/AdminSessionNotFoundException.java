package fscms.cmm.exception;

public class AdminSessionNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AdminSessionNotFoundException() {}
	
	public AdminSessionNotFoundException(String s) {
		super(s);
	}

}