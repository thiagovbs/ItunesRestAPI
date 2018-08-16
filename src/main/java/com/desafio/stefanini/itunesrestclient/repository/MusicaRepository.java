package com.desafio.stefanini.itunesrestclient.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desafio.stefanini.itunesrestclient.model.api.Musica;

@Repository
public interface MusicaRepository  extends JpaRepository<Musica, Integer>{

    @Query("select a from Musica a where a.nome like %?1%")
	public abstract List<Musica> findByNome(String s);

}
