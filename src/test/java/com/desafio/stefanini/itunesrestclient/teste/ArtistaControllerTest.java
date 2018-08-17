package com.desafio.stefanini.itunesrestclient.teste;


import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import io.restassured.response.Response;

public class ArtistaControllerTest {
	
	@Test
	public void listarVerificaCabecalhosResposta() {
		get("/ItunesRestClient/artista").
			then().
			statusCode(200).
				and().
			contentType(JSON);
	}
	
	@Test
	public void listaArtistaValido() {
		get("/ItunesRestClient/artista/jUnit").
		then().
		statusCode(200).body("[0].entity.id", equalTo(260));
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
	public void alteraArtistaExistente() {
		Response response = get("/ItunesRestClient/artista/Teste de Inclusao");
		Integer id = response.jsonPath().get("[0].entity.id");
		String corpo = "{\"id\":"+id+",\"artista_id\" : 1485521, \"genero\":{\"nome\":\"blablabla\"},\"nome\":\"Teste de Inclusao Alterado\"	}"; 
		
		given().
		contentType(JSON).
		body(corpo).
		patch("/ItunesRestClient/artista").
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
