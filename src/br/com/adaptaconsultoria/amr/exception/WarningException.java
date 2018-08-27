package br.com.adaptaconsultoria.amr.exception;

/**
 * 
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class WarningException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WarningException() {
		super();
	}

	public WarningException(String message, Throwable cause) {
		super(message, cause);
	}

	public WarningException(String message) {
		super(message);
	}

	public WarningException(Throwable cause) {
		super(cause);
	}

}