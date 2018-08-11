package com.desafio.stefanini.itunesrestclient.teste;


import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class ItunesRestClientTest {
	
	@Test
	public void listarVerificaCabecalhosResposta() {
		get("/ItunesRestClient/artista/listar").
			then().
			statusCode(200).
				and().
			contentType(JSON);
	}
	
	@Test
	public void listaMusicaValida() {
		get("/ItunesRestClient/artista/listar?musica=Musica 1 do Thiago alterada").
			then().
			statusCode(200).body("trackId", equalTo("[111]"));
	}
	
	@Test
	public void listaArtistaValido() {
		get("/ItunesRestClient/artista/listar?artista=Thiago Veloso").
			then().
			statusCode(200).body("artistId", equalTo("[157]"));
	}
	
	@Test
	public void listaAlbumValido() {
		get("/ItunesRestClient/artista/listar?album=Thiago CD 1 alterado").
			then().
			statusCode(200).body("artistcollectionId", equalTo("[111]"));
	}

	@Test
	public void artistaRetorna404() {
		get("/ItunesRestClient/artista/url_errada").then().statusCode(404);
	}
	
	@Test
	public void gravaNovoArtista() {
		given().
			contentType(JSON).
			body("{\"artistId\" : 555,\"artistName\" : \"Teste de Inclusao\"}").
			when().
			put("/ItunesRestClient/artista/inserir").
			then().
			statusCode(200).
			log().all().
			and().
			body(equalTo("Incluído com sucesso"));
		
	}
	
	@Test
	public void apagaArtistaExistente() {
		delete("/ItunesRestClient/artista/deletar?id_artista=555").
		then().
		statusCode(200).
		and().
		body(equalTo("Excluído com sucesso"));
		
		
	}

}
