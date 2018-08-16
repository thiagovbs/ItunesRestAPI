package com.desafio.stefanini.itunesrestclient.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.stefanini.itunesrestclient.model.api.Genero;
import com.desafio.stefanini.itunesrestclient.model.api.Musica;
import com.desafio.stefanini.itunesrestclient.repository.MusicaRepository;

@Service
public class MusicaService {

	@Autowired
	private MusicaRepository musicaRepository;	
	
    public MusicaService(){
    	
    }
    
    public Musica getById(Integer id) {
    	Optional<Musica> a =  musicaRepository.findById(id);
    	if (a.isPresent()) {
    		return a.get();
    	}else {
    		return null;
    	}
    	
    }
    
    public List<Musica> getMusica(String musica)
    {
        return musicaRepository.findByNome(musica);
    }
    
    public Musica findByIdArtista(Integer id)
    {
        return musicaRepository.findByIdArtista(id);
    }
    
    public Musica findByNomeAlbum(String album)
    {
        return musicaRepository.findByNomeAlbum(album);
    }

    public void addMusica(Musica m)
    {
    	musicaRepository.save(m);
    }

    public void editMusica(Musica m)
    {
    	Optional<Musica> m2 = musicaRepository.findById(m.getId());
        if (m2.isPresent()) {
	    	m2.get().setId(m.getId());
	    		
	    		Genero g = new Genero();
	    		g.setId(m.getGenero().getId());
	    		g.setNome(m.getGenero().getNome());
	    		
	    	m2.get().setGenero(g);
	    	m2.get().setUrl(m.getUrl());
	    	m2.get().setAlbum(m.getAlbum());
	    	m2.get().setDataLancamento(m.getDataLancamento());
	    	m2.get().setDuracao(m.getDuracao());
	    	m2.get().setIsStremable(m.getIsStremable());
	    	m2.get().setNome(m.getNome());
	    	m2.get().setNumeroFaixa(m.getNumeroFaixa());
	    	m2.get().setPreco(m.getPreco());
	    	
	    	
	    	musicaRepository.save(m2.get());
	    }
	    	
	}

    public void delMusica(Musica a){
    	musicaRepository.delete(a);
    }
	
}
