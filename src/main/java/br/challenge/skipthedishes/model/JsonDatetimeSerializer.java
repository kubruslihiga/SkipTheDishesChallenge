package br.challenge.skipthedishes.model;

public class JsonDatetimeSerializer extends JsonDateSerializer {

	public JsonDatetimeSerializer() {
		format = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    }

}
