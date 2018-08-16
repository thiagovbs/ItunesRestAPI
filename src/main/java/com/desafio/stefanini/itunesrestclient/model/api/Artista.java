package com.desafio.stefanini.itunesrestclient.model.api;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.desafio.stefanini.itunesrestclient.model.api.Genero;

@Entity
public class Artista implements EntidadeGenerica{

	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
	private Integer id;
	private BigInteger artista_id;
	private String nome; 
	private String url; 
	
	@ManyToOne
	@MapsId
	@JoinColumn(name = "id_genero")
	private Genero genero;
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	public BigInteger getArtista_id() {
		return artista_id;
	}
	public void setArtista_id(BigInteger id_artista) {
		this.artista_id = id_artista;
	}
	public Artista() {
		super();
	}
	
	
}
