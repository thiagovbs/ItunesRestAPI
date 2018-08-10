package com.desafio.stefanini.itunesrestclient.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="artista")
public class Artista
{

    public Artista()
    {
    }

    public String getWrapperType()
    {
        return wrapperType;
    }

    public void setWrapperType(String wrapperType)
    {
        this.wrapperType = wrapperType;
    }

    public String getKind()
    {
        return kind;
    }

    public void setKind(String kind)
    {
        this.kind = kind;
    }

    public Integer getArtistId()
    {
        return artistId;
    }

    public void setArtistId(Integer artistId)
    {
        this.artistId = artistId;
    }

    public Integer getArtistcollectionId()
    {
        return artistcollectionId;
    }

    public void setArtistcollectionId(Integer artistcollectionId)
    {
        this.artistcollectionId = artistcollectionId;
    }

    public Integer getTrackId()
    {
        return trackId;
    }

    public void setTrackId(Integer trackId)
    {
        this.trackId = trackId;
    }

    public String getArtistName()
    {
        return artistName;
    }

    public void setArtistName(String artistName)
    {
        this.artistName = artistName;
    }

    public String getCollectionName()
    {
        return collectionName;
    }

    public void setCollectionName(String collectionName)
    {
        this.collectionName = collectionName;
    }

    public String getTrackName()
    {
        return trackName;
    }

    public void setTrackName(String trackName)
    {
        this.trackName = trackName;
    }

    public String getCollectionCensoredName()
    {
        return collectionCensoredName;
    }

    public void setCollectionCensoredName(String collectionCensoredName)
    {
        this.collectionCensoredName = collectionCensoredName;
    }

    public String getTrackCensoredName()
    {
        return trackCensoredName;
    }

    public void setTrackCensoredName(String trackCensoredName)
    {
        this.trackCensoredName = trackCensoredName;
    }

    public String getArtistViewUrl()
    {
        return artistViewUrl;
    }

    public void setArtistViewUrl(String artistViewUrl)
    {
        this.artistViewUrl = artistViewUrl;
    }

    public String getCollectionViewUrl()
    {
        return collectionViewUrl;
    }

    public void setCollectionViewUrl(String collectionViewUrl)
    {
        this.collectionViewUrl = collectionViewUrl;
    }

    public String getTrackViewUrl()
    {
        return trackViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl)
    {
        this.trackViewUrl = trackViewUrl;
    }

    public String getPreviewUrl()
    {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl)
    {
        this.previewUrl = previewUrl;
    }

    public String getArtworkUrl30()
    {
        return artworkUrl30;
    }

    public void setArtworkUrl30(String artworkUrl30)
    {
        this.artworkUrl30 = artworkUrl30;
    }

    public String getArtworkUrl60()
    {
        return artworkUrl60;
    }

    public void setArtworkUrl60(String artworkUrl60)
    {
        this.artworkUrl60 = artworkUrl60;
    }

    public String getArtworkUrl100()
    {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100)
    {
        this.artworkUrl100 = artworkUrl100;
    }

    public Double getCollectionPrice()
    {
        return collectionPrice;
    }

    public void setCollectionPrice(Double collectionPrice)
    {
        this.collectionPrice = collectionPrice;
    }

    public Double getTrackPrice()
    {
        return trackPrice;
    }

    public void setTrackPrice(Double trackPrice)
    {
        this.trackPrice = trackPrice;
    }

    public Date getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public String getCollectionExplicitness()
    {
        return collectionExplicitness;
    }

    public void setCollectionExplicitness(String collectionExplicitness)
    {
        this.collectionExplicitness = collectionExplicitness;
    }

    public String getTrackExplicitness()
    {
        return trackExplicitness;
    }

    public void setTrackExplicitness(String trackExplicitness)
    {
        this.trackExplicitness = trackExplicitness;
    }

    public Integer getDiscCount()
    {
        return discCount;
    }

    public void setDiscCount(Integer discCount)
    {
        this.discCount = discCount;
    }

    public Integer getDiscNumber()
    {
        return discNumber;
    }

    public void setDiscNumber(Integer discNumber)
    {
        this.discNumber = discNumber;
    }

    public Integer getTrackCount()
    {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount)
    {
        this.trackCount = trackCount;
    }

    public Integer getTrackNumber()
    {
        return trackNumber;
    }

    public void setTrackNumber(Integer trackNumber)
    {
        this.trackNumber = trackNumber;
    }

    public Integer getTrackTimeMillis()
    {
        return trackTimeMillis;
    }

    public void setTrackTimeMillis(Integer trackTimeMillis)
    {
        this.trackTimeMillis = trackTimeMillis;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public String getPrimaryGenreName()
    {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName)
    {
        this.primaryGenreName = primaryGenreName;
    }

    public Boolean getIsStreamable()
    {
        return isStreamable;
    }

    public void setIsStreamable(Boolean isStreamable)
    {
        this.isStreamable = isStreamable;
    }

    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private Integer id;
    String wrapperType;
    String kind;
    Integer artistId;
    Integer artistcollectionId;
    Integer trackId;
    String artistName;
    String collectionName;
    String trackName;
    String collectionCensoredName;
    String trackCensoredName;
    String artistViewUrl;
    String collectionViewUrl;
    String trackViewUrl;
    String previewUrl;
    String artworkUrl30;
    String artworkUrl60;
    String artworkUrl100;
    Double collectionPrice;
    Double trackPrice;
    Date releaseDate;
    String collectionExplicitness;
    String trackExplicitness;
    Integer discCount;
    Integer discNumber;
    Integer trackCount;
    Integer trackNumber;
    Integer trackTimeMillis;
    String country;
    String currency;
    String primaryGenreName;
    Boolean isStreamable;
}
