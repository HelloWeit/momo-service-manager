package cn.weit.happymo.exception;

/**
 * @author weitong
 */
public class MoException extends RuntimeException {
	private Integer code;

	private MoException(Integer code, String message) {
		super(message);
		this.code = code;
	}

	public MoException(ExceptionCode exceptionCode) {
		this(exceptionCode.getCode(), exceptionCode.getMessage());
	}
}
