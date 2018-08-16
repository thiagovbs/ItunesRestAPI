package com.desafio.stefanini.itunesrestclient.model.itunes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class TrackReturn
{

    public TrackReturn()
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

    public List<Track> getResults()
    {
        return results;
    }

    public void setResults(List<Track> results)
    {
        this.results = results;
    }

    Integer resultCount;
    List<Track> results;
}

