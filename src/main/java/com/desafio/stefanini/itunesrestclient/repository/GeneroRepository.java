package com.desafio.stefanini.itunesrestclient.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desafio.stefanini.itunesrestclient.model.api.Genero;

@Repository
public interface GeneroRepository  extends JpaRepository<Genero, Integer>{

    @Query("select a from Genero a where a.nome like %?1%")
	public abstract List<Genero> findByNome(String s);
    
}
