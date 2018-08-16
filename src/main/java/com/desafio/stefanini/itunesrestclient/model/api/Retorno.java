package com.desafio.stefanini.itunesrestclient.model.api;

public class Retorno {
	
	private String codigo;
	private TipoRetorno tipo;
	private EntidadeGenerica entity;
	private String erro;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public TipoRetorno getTipo() {
		return tipo;
	}
	public void setTipo(TipoRetorno tipo) {
		this.tipo = tipo;
	}
	public EntidadeGenerica getEntity() {
		return entity;
	}
	public void setEntity(EntidadeGenerica entity) {
		this.entity = entity;
	}
	public String getErro() {
		return erro;
	}
	public void setErro(String erro) {
		this.erro = erro;
	}
	public Retorno() {
		super();
	}
	
	

}
