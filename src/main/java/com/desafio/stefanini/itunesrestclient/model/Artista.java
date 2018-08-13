package com.desafio.stefanini.itunesrestclient.model;

public class Artista
{

	String wrapperType; 
	String artistType; 
	String artistName; 
	String artistLinkUrl; 
	Integer artistId; 
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
	public Integer getArtistId() {
		return artistId;
	}
	public void setArtistId(Integer artistId) {
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
	public Artista() {
		super();
	}
	
	
}
