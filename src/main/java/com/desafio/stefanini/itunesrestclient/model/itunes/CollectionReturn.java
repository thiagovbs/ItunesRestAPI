package com.desafio.stefanini.itunesrestclient.model.itunes;

import java.util.List;

public class CollectionReturn {
	Integer resultCount;
    List<Collection> results;
	public Integer getResultCount() {
		return resultCount;
	}
	public void setResultCount(Integer resultCount) {
		this.resultCount = resultCount;
	}
	public List<Collection> getResults() {
		return results;
	}
	public void setResults(List<Collection> results) {
		this.results = results;
	}
	public CollectionReturn() {
		super();
	}
    
    
}
