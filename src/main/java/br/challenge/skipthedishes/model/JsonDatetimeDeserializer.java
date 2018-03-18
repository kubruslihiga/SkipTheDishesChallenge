package br.challenge.skipthedishes.model;

public class JsonDatetimeDeserializer extends JsonDateDeserializer {

	public JsonDatetimeDeserializer() {
		this.format = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
	}

}
