package com.desafio.stefanini.itunesrestclient.repository;

import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desafio.stefanini.itunesrestclient.model.api.Artista;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Integer>
{
    @Query("select a from Artista a where a.nome like %?1%")
	public abstract List<Artista> findByNome(String s);
    
    @Query("select a from Artista a where a.artista_id like ?1")
	public abstract Artista findByArtista_Id(BigInteger id_artista);    
}
