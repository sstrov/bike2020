package fscms.cmm.exception;

public class NotAllowFileExtException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public NotAllowFileExtException() {}
	
	public NotAllowFileExtException(String s) {
		super(s);
	}

}