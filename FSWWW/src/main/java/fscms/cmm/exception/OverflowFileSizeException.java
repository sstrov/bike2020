package fscms.cmm.exception;

public class OverflowFileSizeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public OverflowFileSizeException() {}
	
	public OverflowFileSizeException(String s) {
		super(s);
	}

}
