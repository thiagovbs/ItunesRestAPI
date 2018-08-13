package com.desafio.stefanini.itunesrestclient.model;

import java.util.List;

public class ArtistaReturn {
	
	Integer resultCount;
    List<Artista> results;
	public Integer getResultCount() {
		return resultCount;
	}
	public void setResultCount(Integer resultCount) {
		this.resultCount = resultCount;
	}
	public List<Artista> getResults() {
		return results;
	}
	public void setResults(List<Artista> results) {
		this.results = results;
	}
	public ArtistaReturn() {
		super();
	}

}
