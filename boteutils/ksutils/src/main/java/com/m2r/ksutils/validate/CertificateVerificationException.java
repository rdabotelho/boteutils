package com.m2r.ksutils.validate;

public class CertificateVerificationException extends Exception {

	private static final long serialVersionUID = 1L;

	private ValidationErro validationErro;

	public CertificateVerificationException(ValidationErro erro) {
		this(erro, null, null);
	}

	public CertificateVerificationException(ValidationErro erro, String msg) {
		this(erro, msg, null);
	}

	public CertificateVerificationException(ValidationErro erro, String msg, Throwable cause) {
		super(msg, cause);
		this.validationErro = erro;
	}

	public ValidationErro getValidationErro() {

		return this.validationErro;
	}

}
