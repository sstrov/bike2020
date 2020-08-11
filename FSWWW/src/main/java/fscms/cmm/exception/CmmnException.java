package fscms.cmm.exception;

public class CmmnException extends Exception {
	private static final long serialVersionUID = 1L;

	public CmmnException() {}

	public CmmnException(String msg) {
		super(msg);
	}

	public CmmnException(Throwable cause) {
		super(cause);
	}

	public CmmnException(String message, Throwable cause) {
		super(message, cause);
	}
}