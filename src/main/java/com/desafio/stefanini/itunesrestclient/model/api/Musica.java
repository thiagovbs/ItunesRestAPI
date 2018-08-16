package com.desafio.stefanini.itunesrestclient.model.api;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Musica implements EntidadeGenerica{
	
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
	private Integer id;
	private String nome; 
	private String url; 
	
	@OneToOne
	@JoinColumn(name = "id_artista")
	private Artista artista;
	
	@OneToOne
	@JoinColumn(name = "id_album")
	private Album album;

	private Double preco;
	private Integer numeroFaixa;
	private Boolean isStremable;
	
	@ManyToOne
	@MapsId
	@JoinColumn(name = "id_genero")
	private Genero genero;
	
	private Date dataLancamento;
	private Double duracao;
	
	public Artista getArtista() {
		return artista;
	}
	public void setArtista(Artista artista) {
		this.artista = artista;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Integer getNumeroFaixa() {
		return numeroFaixa;
	}
	public void setNumeroFaixa(Integer numeroFaixa) {
		this.numeroFaixa = numeroFaixa;
	}
	public Boolean getIsStremable() {
		return isStremable;
	}
	public void setIsStremable(Boolean isStremable) {
		this.isStremable = isStremable;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public Date getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public Double getDuracao() {
		return duracao;
	}
	public void setDuracao(Double duracao) {
		this.duracao = duracao;
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
	public Musica() {
		super();
	}
	

	
	
	
}
