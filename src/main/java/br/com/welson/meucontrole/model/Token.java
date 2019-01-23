package br.com.welson.meucontrole.model;

import java.time.LocalDateTime;

public class Token {

	private String token;
	private LocalDateTime expiracao;

	public Token(String token, LocalDateTime expiracao) {
		super();
		this.token = token;
		this.expiracao = expiracao;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getExpiracao() {
		return expiracao;
	}

	public void setExpiracao(LocalDateTime expiracao) {
		this.expiracao = expiracao;
	}

}
