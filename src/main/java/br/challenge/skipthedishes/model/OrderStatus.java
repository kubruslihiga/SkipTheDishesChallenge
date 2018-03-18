package br.challenge.skipthedishes.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {

	CREATED, PAID, NOT_PAID, REVERTED, CANCELED;

	@JsonValue
	public String jsonValue() {
		return name();
	}
}
