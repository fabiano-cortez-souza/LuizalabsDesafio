package br.com.luzialabs.desafio.agenda.http;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class HttpStatusReference implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("rel")
	private String rel;

	@JsonProperty("href")
	private String href;
	
	public HttpStatusReference() {}

	public HttpStatusReference(String rel, String href) {
		super();
		this.rel = rel;
		this.href = href;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
}
