package com.desafio.stefanini.itunesrestclient.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class ArtistsReturn
{

    public ArtistsReturn()
    {
    }

    public Integer getResultCount()
    {
        return resultCount;
    }

    public void setResultCount(Integer resultCount)
    {
        this.resultCount = resultCount;
    }

    public List<Artista> getResults()
    {
        return results;
    }

    public void setResults(List<Artista> results)
    {
        this.results = results;
    }

    Integer resultCount;
    List<Artista> results;
}

