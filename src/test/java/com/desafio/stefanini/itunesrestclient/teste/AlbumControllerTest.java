package com.desafio.stefanini.itunesrestclient.teste;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import io.restassured.response.Response;

public class AlbumControllerTest {

	@Test
	public void listarVerificaCabecalhosResposta() {
		get("/ItunesRestClient/album").
			then().
			statusCode(200).
				and().
			contentType(JSON);
	}

	@Test
	public void listaAlbumValido() {
		get("/ItunesRestClient/album/ALBUM JUNIT").
			then().
			statusCode(200).body("[0].entity.id", equalTo(128));
	}
	
	@Test
	public void gravaNovoAlbum() {
		given().
			contentType(JSON).
			body("{ \"nome\": \"ALBUM JUNIT NOVO\", \"genero\": {\"nome\": \"jUnit\" },\"artista\": { \"id\": 260,\"artista_id\": 157890, \"nome\": \"jUnit\", \"url\": \"lalalalala\",  \"genero\": { \"nome\": \"jUnit\" } } }").
			put("/ItunesRestClient/album").
			then().
			statusCode(200);
	}
	
	@Test
	public void alteraAlbumExistente() {
		Response response = get("/ItunesRestClient/album/ALBUM JUNIT NOVO");
		Integer id = response.jsonPath().get("[0].entity.id");
		String corpo = "{\"id\":"+id+", \"nome\": \"ALBUM JUNIT NOVO ALTERADO\", \"genero\": {\"nome\": \"jUnit\" },\"artista\": { \"id\": 260,\"artista_id\": 157890, \"nome\": \"jUnit\", \"url\": \"lalalalala\",  \"genero\": { \"nome\": \"jUnit\" } } }"; 
		
		given().
		contentType(JSON).
		body(corpo).
		patch("/ItunesRestClient/album").
		then().
		statusCode(200);
	}

	@Test
	public void apagaAlbumExistente() {
		
		Response response = get("/ItunesRestClient/album/ALBUM JUNIT NOVO ALTERADO");
		Integer id = response.jsonPath().get("[0].entity.id");
		
		delete("/ItunesRestClient/album/"+id).
		then().
		statusCode(200);
	}
	
}
