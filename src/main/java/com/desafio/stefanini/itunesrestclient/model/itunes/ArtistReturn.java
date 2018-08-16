package com.desafio.stefanini.itunesrestclient.model.itunes;

import java.util.List;

public class ArtistReturn {
	
	Integer resultCount;
    List<Artist> results;
	public Integer getResultCount() {
		return resultCount;
	}
	public void setResultCount(Integer resultCount) {
		this.resultCount = resultCount;
	}
	public List<Artist> getResults() {
		return results;
	}
	public void setResults(List<Artist> results) {
		this.results = results;
	}
	public ArtistReturn() {
		super();
	}

}
