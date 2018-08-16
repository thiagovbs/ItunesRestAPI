package com.desafio.stefanini.itunesrestclient.model.api;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.desafio.stefanini.itunesrestclient.model.api.Artista;

@Entity
public class Album implements EntidadeGenerica {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_album")
	private Integer id;
	
	private String nome; 
	private String url; 
	
	@ManyToOne
	@JoinColumn(name = "id_genero")
	private Genero genero;
	
	@OneToOne
	@JoinColumn(name = "id_artista")
	private Artista artista;
	
	private Double preco;
	private Date data_lancamento;
	private Integer qtd_musicas;
	
	public Artista getArtista() {
		return artista;
	}
	public void setArtista(Artista artista) {
		this.artista = artista;
	}
	
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public Date getData_lancamento() {
		return data_lancamento;
	}
	public void setData_lancamento(Date data_lancamento) {
		this.data_lancamento = data_lancamento;
	}
	public Integer getQtd_musicas() {
		return qtd_musicas;
	}
	public void setQtd_musicas(Integer qtd_musicas) {
		this.qtd_musicas = qtd_musicas;
	}
	
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
	public Album() {
		super();
	}
	

}
