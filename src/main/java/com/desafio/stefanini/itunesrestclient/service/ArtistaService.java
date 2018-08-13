package com.desafio.stefanini.itunesrestclient.service;

import com.desafio.stefanini.itunesrestclient.model.Musica;
import com.desafio.stefanini.itunesrestclient.repository.ArtistaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistaService
{

    public ArtistaService(){
    	
    }
    
    
    public List<Musica> getArtista(String artista)
    {
        return artistaRepository.findByArtistName(artista);
    }

    public Musica buscarArtistaPorId(Integer id_artista, String album, Integer id_musica)
    {
        return artistaRepository.buscarArtistaPorId(id_artista, album, id_musica);
    }

    public List<Musica> getAllAlbuns(String album)
    {
        return artistaRepository.findByCollectionName(album);
    }

    public List<Musica> getAllMusicas(String musica)
    {
        return artistaRepository.findByTrackName(musica);
    }

    public List<Musica> buscarArtistas(String artista, String album, String musica)
    {
        return artistaRepository.buscarArtistas(artista, album, musica);
    }

    public void addArtista(Musica a)
    {
        artistaRepository.save(a);
    }

    public void editArtista(Musica a)
    {
        Musica a2 = artistaRepository.findByArtistId(a.getArtistId());
        a2.setWrapperType(a.getWrapperType());
        a2.setKind(a.getKind());
        a2.setArtistId(a.getArtistId());
        a2.setArtistcollectionId(a.getArtistcollectionId());
        a2.setTrackId(a.getTrackId());
        a2.setArtistName(a.getArtistName());
        a2.setCollectionName(a.getCollectionName());
        a2.setTrackName(a.getTrackName());
        a2.setCollectionCensoredName(a.getCollectionCensoredName());
        a2.setTrackCensoredName(a.getTrackCensoredName());
        a2.setArtistViewUrl(a.getArtistViewUrl());
        a2.setCollectionViewUrl(a.getCollectionViewUrl());
        a2.setTrackViewUrl(a.getTrackViewUrl());
        a2.setPreviewUrl(a.getPreviewUrl());
        a2.setArtworkUrl30(a.getArtworkUrl30());
        a2.setArtworkUrl60(a.getArtworkUrl60());
        a2.setCollectionPrice(a.getCollectionPrice());
        a2.setTrackPrice(a.getTrackPrice());
        a2.setReleaseDate(a.getReleaseDate());
        a2.setCollectionExplicitness(a.getCollectionExplicitness());
        a2.setTrackExplicitness(a.getTrackExplicitness());
        a2.setDiscCount(a.getDiscCount());
        a2.setDiscNumber(a.getDiscNumber());
        a2.setTrackNumber(a.getTrackNumber());
        a2.setCountry(a.getCountry());
        a2.setCurrency(a.getCurrency());
        a2.setPrimaryGenreName(a.getPrimaryGenreName());
        a2.setIsStreamable(a.getIsStreamable());
        artistaRepository.save(a2);
    }

    public void delArtista(Musica a)
    {
        artistaRepository.delete(a);
    }

    @Autowired
    private ArtistaRepository artistaRepository;
}
