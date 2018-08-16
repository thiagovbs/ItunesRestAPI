package com.desafio.stefanini.itunesrestclient.service;

import com.desafio.stefanini.itunesrestclient.model.api.Genero;
import com.desafio.stefanini.itunesrestclient.repository.GeneroRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroService
{

	@Autowired
	private GeneroRepository generoRepository;	
	
    public GeneroService(){
    	
    }
    
    
    public Genero getById(Integer id) {
    	Optional<Genero> g =  generoRepository.findById(id);
    	if (g.isPresent()) {
    		return g.get();
    	}else {
    		return null;
    	}
    }
    
    public List<Genero> findByNome(String genero) {
    	return generoRepository.findByNome(genero);
    	
    }
    
    public List<Genero> getGenero(String genero)
    {
        return generoRepository.findByNome(genero);
    }

    public void addGenero(Genero g)
    {
    	generoRepository.save(g);
    }

    public void editGenero(Genero g)
    {
    	Optional<Genero> g2 = generoRepository.findById(g.getId());
        if (g2.isPresent()) {
	    	g2.get().setId(g.getId());
	    	g2.get().setNome(g.getNome());
	    		
	    	
	    	generoRepository.save(g2.get());
	    }
	    	
	}

    public void delGenero(Genero g)
    {
    	generoRepository.delete(g);
    }
}
