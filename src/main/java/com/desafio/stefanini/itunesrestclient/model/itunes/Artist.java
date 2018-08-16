package com.desafio.stefanini.itunesrestclient.model.itunes;

import java.math.BigInteger;

public class Artist
{

	String wrapperType; 
	String artistType; 
	String artistName; 
	String artistLinkUrl; 
	BigInteger artistId; 
	Integer amgArtistId; 
	String primaryGenreName; 
	Integer primaryGenreId;
	
	public String getWrapperType() {
		return wrapperType;
	}
	public void setWrapperType(String wrapperType) {
		this.wrapperType = wrapperType;
	}
	public String getArtistType() {
		return artistType;
	}
	public void setArtistType(String artistType) {
		this.artistType = artistType;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getArtistLinkUrl() {
		return artistLinkUrl;
	}
	public void setArtistLinkUrl(String artistLinkUrl) {
		this.artistLinkUrl = artistLinkUrl;
	}
	public BigInteger getArtistId() {
		return artistId;
	}
	public void setArtistId(BigInteger artistId) {
		this.artistId = artistId;
	}
	public Integer getAmgArtistId() {
		return amgArtistId;
	}
	public void setAmgArtistId(Integer amgArtistId) {
		this.amgArtistId = amgArtistId;
	}
	public String getPrimaryGenreName() {
		return primaryGenreName;
	}
	public void setPrimaryGenreName(String primaryGenreName) {
		this.primaryGenreName = primaryGenreName;
	}
	public Integer getPrimaryGenreId() {
		return primaryGenreId;
	}
	public void setPrimaryGenreId(Integer primaryGenreId) {
		this.primaryGenreId = primaryGenreId;
	}
	public Artist() {
		super();
	}
	
	
}
