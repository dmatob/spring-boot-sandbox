package com.dmatob.sandbox.springboot.application.exception;

public class DuplicatedArticleException extends RuntimeException {

	private static final long serialVersionUID = 8241428589532194515L;

	public DuplicatedArticleException(final String message) {
        super(message);
    }
}
