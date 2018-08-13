package com.desafio.stefanini.itunesrestclient.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class MusicaReturn
{

    public MusicaReturn()
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

    public List<Musica> getResults()
    {
        return results;
    }

    public void setResults(List<Musica> results)
    {
        this.results = results;
    }

    Integer resultCount;
    List<Musica> results;
}

