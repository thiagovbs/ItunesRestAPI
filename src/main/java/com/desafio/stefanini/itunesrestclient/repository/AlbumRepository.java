package com.desafio.stefanini.itunesrestclient.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desafio.stefanini.itunesrestclient.model.api.Album;

@Repository
public interface AlbumRepository  extends JpaRepository<Album, Integer>{

    @Query("select a from Album a where a.artista.id = ?1")
	public abstract Album findByIdArtista(Integer id);
    
    @Query("select a from Album a where a.nome like %?1%")
	public abstract List<Album> findByNome(String s);

}
