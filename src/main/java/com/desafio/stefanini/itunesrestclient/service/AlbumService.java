package com.desafio.stefanini.itunesrestclient.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.stefanini.itunesrestclient.model.api.Album;
import com.desafio.stefanini.itunesrestclient.model.api.Genero;
import com.desafio.stefanini.itunesrestclient.repository.AlbumRepository;

@Service
public class AlbumService {

	@Autowired
	private AlbumRepository albumRepository;	
	
    public AlbumService(){
    	
    }
    
    public Album getById(Integer id) {
    	Optional<Album> a =  albumRepository.findById(id);
    	if (a.isPresent()) {
    		return a.get();
    	}else {
    		return null;
    	}
    	
    }
    
    public List<Album> getAlbum(String album)
    {
        return albumRepository.findByNome(album);
    }
    
    public Album findByIdArtista(Integer id)
    {
        return albumRepository.findByIdArtista(id);
    }

    public void addAlbum(Album a)
    {
    	albumRepository.save(a);
    }

    public void editAlbum(Album a)
    {
    	Optional<Album> a2 = albumRepository.findById(a.getId());
        if (a2.isPresent()) {
	    	a2.get().setId(a.getId());
	    		
	    		Genero g = new Genero();
	    		g.setId(a.getGenero().getId());
	    		g.setNome(a.getGenero().getNome());
	    		
	    	a2.get().setGenero(g);
	    	a2.get().setUrl(a.getUrl());
	    	a2.get().setData_lancamento(a.getData_lancamento());
	    	a2.get().setPreco(a.getPreco());
	    	a2.get().setArtista(a.getArtista());
	    	a2.get().setQtd_musicas(a.getQtd_musicas());
	    	a2.get().setNome(a.getNome());
	    	
	    	albumRepository.save(a2.get());
	    }
	    	
	}

    public void delAlbum(Album a)
    {
    	albumRepository.delete(a);
    }
	
}
