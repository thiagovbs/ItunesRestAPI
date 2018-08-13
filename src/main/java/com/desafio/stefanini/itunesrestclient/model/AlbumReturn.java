package com.desafio.stefanini.itunesrestclient.model;

import java.util.List;

public class AlbumReturn {
	Integer resultCount;
    List<Album> results;
	public Integer getResultCount() {
		return resultCount;
	}
	public void setResultCount(Integer resultCount) {
		this.resultCount = resultCount;
	}
	public List<Album> getResults() {
		return results;
	}
	public void setResults(List<Album> results) {
		this.results = results;
	}
	public AlbumReturn() {
		super();
	}
    
    
}
