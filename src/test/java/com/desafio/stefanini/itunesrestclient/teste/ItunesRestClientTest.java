package com.desafio.stefanini.itunesrestclient.teste;


import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import io.restassured.response.Response;

public class ItunesRestClientTest {
	
	@Test
	public void listarVerificaCabecalhosResposta() {
		get("/ItunesRestClient/artista").
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
	public void listaArtistaValido() {
		
		get("/ItunesRestClient/artista/jUnit").
		then().
		statusCode(200).body("[0].entity.id", equalTo(260));
		
			
	}
	
	@Test
	public void listaAlbumValido() {
		get("/ItunesRestClient/album/ALBUM JUNIT").
			then().
			statusCode(200).body("[0].entity.id", equalTo(128));
	}

	@Test
	public void artistaRetorna404() {
		get("/ItunesRestClient/url_errada").then().statusCode(404);
	}
	
	@Test
	public void gravaNovoArtista() {
		given().
			contentType(JSON).
			body("{\"id\":1,\"artista_id\" : 1485521,\"genero\" : {\"nome\":\"Generico\"}, \"nome\":\"Teste de Inclusao\",\"url\":\"www.teste.com.br\"	}").
			put("/ItunesRestClient/artista").
			then().
			statusCode(200);
		
	}
	
	@Test
	public void apagaArtistaExistente() {
		
		Response response = get("/ItunesRestClient/artista/Teste de Inclusao");
		
		Integer id = response.jsonPath().get("[0].entity.id");
		
		delete("/ItunesRestClient/artista/"+id).
		then().
		statusCode(200);
		
		
	}

}
