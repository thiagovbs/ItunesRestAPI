package com.desafio.stefanini.itunesrestclient.teste;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import io.restassured.response.Response;

public class MusicaControllerTest {
	
	@Test
	public void listarVerificaCabecalhosResposta() {
		get("/ItunesRestClient/musica").
			then().
			statusCode(200).
				and().
			contentType(JSON);
	}
	
	
	@Test
	public void listaMusicaValida() {
		get("/ItunesRestClient/musica/Musica jUnit").
			then().
			statusCode(200).body("[0].entity.id", equalTo(150));
	}
	
	@Test
	public void gravaNovaMusica() {
		given().
			contentType(JSON).
			body("{\"nome\": \"MUSICA TESTE\", \"album\": {\r\n" + 
					"\"id\": 110, \"nome\": \"Multishow Ao Vivo - Skank No Mineirão\", \"genero\": {\r\n" + 
					" \"id\": 104,\r\n" + 
					" \"nome\": \"Brasileira\"\r\n" + 
					"}, \"artista\": { \"id\": 240,\r\n" + 
					" \"artista_id\": 157890919, \"nome\": \"Skank\",\r\n" + 
					" \"url\": \"https://itunes.apple.com/br/artist/skank/157890919?uo=4\", \"genero\": {\r\n" + 
					" \"id\": 104, \"nome\": \"Brasileira\"\r\n" + 
					" }\r\n" + 
					" }, \"preco\": 9.99, \"qtd_musicas\": 10 },\r\n" + 
					" \"preco\": 1.99, \"numeroFaixa\": 5,\r\n" + 
					" \"genero\": {\r\n" + 
					"\"id\": 104,\r\n" + 
					" \"nome\": \"Brasileira\"\r\n" + 
					"},\r\n" + 
					" \"duracao\": 283947\r\n" + 
					"}").
			put("/ItunesRestClient/musica").
			then().
			statusCode(200);
	}
	
	@Test
	public void alteraMusicaExistente() {
		Response response = get("/ItunesRestClient/musica/MUSICA TESTE");
		Integer id = response.jsonPath().get("[0].entity.id");
		String corpo = "{\"id\":"+id+","+
				        "\"nome\": \"MUSICA TESTE ALTERADA\", \"album\": {\r\n" + 
						 "\"id\": 110, \"nome\": \"Multishow Ao Vivo - Skank No Mineirão\", \"genero\": {\r\n" + 
						 " \"id\": 104,\r\n" + 
						 " \"nome\": \"Brasileira\"\r\n" + 
						 "}, \"artista\": { \"id\": 240,\r\n" + 
						 " \"artista_id\": 157890919, \"nome\": \"Skank\",\r\n" + 
						 " \"url\": \"https://itunes.apple.com/br/artist/skank/157890919?uo=4\", \"genero\": {\r\n" + 
						 " \"id\": 104, \"nome\": \"Brasileira\"\r\n" + 
						 " }\r\n" + 
						 " }, \"preco\": 9.99, \"qtd_musicas\": 10 },\r\n" + 
						 " \"preco\": 1.99, \"numeroFaixa\": 5,\r\n" + 
						 " \"genero\": {\r\n" + 
						 "\"id\": 104,\r\n" + 
						 " \"nome\": \"Brasileira\"\r\n" + 
						 "},\r\n" + 
						 " \"duracao\": 283947\r\n" + 
						 "}"; 
		
		given().
		contentType(JSON).
		body(corpo).
		patch("/ItunesRestClient/musica").
		then().
		statusCode(200);
	}

	@Test
	public void apagaMusicaExistente() {
		
		Response response = get("/ItunesRestClient/musica/MUSICA TESTE ALTERADA");
		Integer id = response.jsonPath().get("[0].entity.id");
		
		delete("/ItunesRestClient/musica/"+id).
		then().
		statusCode(200);
	}
	


}
