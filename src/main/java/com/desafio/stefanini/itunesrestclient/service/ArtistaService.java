package com.desafio.stefanini.itunesrestclient.service;

import com.desafio.stefanini.itunesrestclient.model.api.Artista;
import com.desafio.stefanini.itunesrestclient.model.api.Genero;
import com.desafio.stefanini.itunesrestclient.repository.ArtistaRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistaService
{

	@Autowired
	private ArtistaRepository artistaRepository;	
	
    public ArtistaService(){
    	
    }
    
    
    public Artista getByArtista_Id(BigInteger id) {
    	return  artistaRepository.findByArtista_Id(id);
    }
    
    public Artista getById(Integer id) {
    	Optional<Artista> a =  artistaRepository.findById(id);
    	if (a.isPresent()) {
    		return a.get();
    	}else {
    		return null;
    	}
    }
    
    public List<Artista> getArtista(String artista)
    {
        return artistaRepository.findByNome(artista);
    }

    public void addArtista(Artista a)
    {
        artistaRepository.save(a);
    }

    public void editArtista(Artista a)
    {
    	Optional<Artista> a2 = artistaRepository.findById(a.getId());
        if (a2.isPresent()) {
	    	a2.get().setId(a.getId());
	    		
	    		Genero g = new Genero();
	    		g.setId(a.getGenero().getId());
	    		g.setNome(a.getGenero().getNome());
	    		
	    	a2.get().setGenero(g);
	    	a2.get().setUrl(a.getUrl());
	    	
	    	artistaRepository.save(a2.get());
	    }
	    	
	}

    public void delArtista(Artista a)
    {
        artistaRepository.delete(a);
    }
}
