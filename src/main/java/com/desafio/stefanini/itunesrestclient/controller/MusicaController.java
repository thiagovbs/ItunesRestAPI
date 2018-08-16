package com.desafio.stefanini.itunesrestclient.controller;

import com.desafio.stefanini.itunesrestclient.model.api.Album;
import com.desafio.stefanini.itunesrestclient.model.api.Artista;
import com.desafio.stefanini.itunesrestclient.model.api.Genero;
import com.desafio.stefanini.itunesrestclient.model.api.Musica;
import com.desafio.stefanini.itunesrestclient.model.api.Retorno;
import com.desafio.stefanini.itunesrestclient.model.api.TipoRetorno;
import com.desafio.stefanini.itunesrestclient.model.itunes.Track;
import com.desafio.stefanini.itunesrestclient.model.itunes.TrackReturn;
import com.desafio.stefanini.itunesrestclient.service.AlbumService;
import com.desafio.stefanini.itunesrestclient.service.ArtistaService;
import com.desafio.stefanini.itunesrestclient.service.GeneroService;
import com.desafio.stefanini.itunesrestclient.service.MusicaService;
import com.google.gson.Gson;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping(value={"/musica"})
public class MusicaController
{
	

	static final String URL_ITUNES = "https://itunes.apple.com/search?country=br&media=music&limit=200&entity=musicTrack&term=";

	 @Autowired
	 private MusicaService musicaService;	 
	
	 @Autowired
	 private AlbumService albumService;
	 
	 @Autowired
	 private ArtistaService artistaService;	 
	 
	 @Autowired
	 private GeneroService generoService;	
	

    public MusicaController()
    {
    }

    
	@ApiOperation(
			value="Lista informações sobre musicas com o termo informado", 
			notes="Essa operação lista informações sobre musicas com o termo informado")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma lista contendo as músicas com o termo informado",
					response=Retorno.class
					),
			@ApiResponse(
					code=404, 
					message="Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
					response=Retorno.class
					)
 
	})    
    @RequestMapping(method=RequestMethod.GET, value={"/lista", "/lista/{musica}"})
	public ResponseEntity<?> listar(@PathVariable(value="musica", required = false) String musica)
    {
        if (musica != null) {
        	return pesquisaMusica(musica);
        }else {
        	return pesquisaMusica("");
        } 
    }
	
	private ResponseEntity<?> pesquisaMusica(String musica){
		List<Musica> m = musicaService.getMusica(musica);
        try
        {
            if(m.isEmpty())
            {
                inclui_mysql(musica);
                m = musicaService.getMusica(musica);
            }
            return ResponseEntity.status(HttpStatus.OK).body( montaResposta(m));
        }
        catch(Exception e)
        {
        	Retorno r = new Retorno();
        	r.setCodigo("404");
        	r.setErro(e.getMessage());
        	r.setTipo(TipoRetorno.ERRO);
            return ResponseEntity.badRequest().body(r);
        }
		
	} 

	@ApiOperation(
			value="Insere uma musica na base de dados", 
			response=Musica.class, 
			notes="Essa operação insere informações de uma musica")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um ResponseEntity com uma mensagem de sucesso",
					response=Retorno.class
					),
			@ApiResponse(
					code=404, 
					message="Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
					response=Retorno.class
					)
 
	})  	
    @PutMapping(value={"/insere"})
    public ResponseEntity<?> inserir(@RequestBody Musica musica)
    {
        try
        {
	        if (musica.getGenero() != null) {	
        		if (generoService.findByNome(musica.getGenero().getNome()).isEmpty()) {
	        		Genero g = new Genero();
	        		g.setNome(musica.getGenero().getNome());
	        	  	generoService.addGenero(g);
	        	}  	
	        	musica.setGenero(generoService.findByNome(musica.getGenero().getNome()).get(0));
	        }	
        	
	        	
	        if (musica.getAlbum() != null) {
	        	if (albumService.findByNome(musica.getAlbum().getNome())==null) {
	        		Album album = new Album();
	        		album.setGenero(generoService.findByNome(musica.getGenero().getNome()).get(0));
	        		album.setUrl(musica.getAlbum().getUrl());
	        		album.setData_lancamento(musica.getAlbum().getData_lancamento());
	        		album.setPreco(musica.getAlbum().getPreco());
	        		album.setArtista(musica.getAlbum().getArtista());
	        		album.setQtd_musicas(musica.getAlbum().getQtd_musicas());
	        		album.setNome(musica.getAlbum().getNome());
	    	    	
	    	    	albumService.addAlbum(album);
	        	}
	        	musica.setAlbum(albumService.findByNome(musica.getAlbum().getNome()).get(0));
	        }	
        	
        	musicaService.addMusica(musica);
        	Retorno r = new Retorno();
         	r.setCodigo("200");
         	r.setErro("");
         	r.setEntity(musica);
         	r.setTipo(TipoRetorno.SUCESSO);
            return ResponseEntity.status(HttpStatus.OK).body(r);
        }
        catch(Exception e)
        {
        	Retorno r = new Retorno();
        	r.setCodigo("404");
        	r.setErro(e.getMessage());
        	r.setTipo(TipoRetorno.ERRO);
            return ResponseEntity.badRequest().body(r);
        }
    }

	@ApiOperation(
			value="Altera uma musica na base de dados", 
			response=Musica.class, 
			notes="Essa operação altera informações de uma musica")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um ResponseEntity com uma mensagem de sucesso",
					response=Retorno.class
					),
			@ApiResponse(
					code=404, 
					message="Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
					response=Retorno.class
					)
 
	}) 	
    @PatchMapping(value={"/altera"})
    public ResponseEntity<?> alterar(@RequestBody Musica musica)
    {
        try
        {
        	if (generoService.findByNome(musica.getGenero().getNome()).isEmpty()) {
        		Genero g = new Genero();
        		g.setNome(musica.getGenero().getNome());
        	  	generoService.addGenero(g);
        	}  	
        	musica.setGenero(generoService.findByNome(musica.getGenero().getNome()).get(0));
        	
        	if (albumService.findByNome(musica.getAlbum().getNome())==null) {
        		Album album = new Album();
        		album.setGenero(generoService.findByNome(musica.getGenero().getNome()).get(0));
        		album.setUrl(musica.getAlbum().getUrl());
        		album.setData_lancamento(musica.getAlbum().getData_lancamento());
        		album.setPreco(musica.getAlbum().getPreco());
        		album.setArtista(musica.getAlbum().getArtista());
        		album.setQtd_musicas(musica.getAlbum().getQtd_musicas());
        		album.setNome(musica.getAlbum().getNome());
    	    	
    	    	albumService.addAlbum(album);
        	}
        	musica.setAlbum(albumService.findByNome(musica.getAlbum().getNome()).get(0));
        	
        	musicaService. editMusica(musica);
        	Retorno r = new Retorno();
         	r.setCodigo("200");
         	r.setErro("");
         	r.setEntity(musica);
         	r.setTipo(TipoRetorno.SUCESSO);
            return ResponseEntity.status(HttpStatus.OK).body(r);
        }
        catch(Exception e)
        {
        	Retorno r = new Retorno();
        	r.setCodigo("404");
        	r.setErro(e.getMessage());
        	r.setTipo(TipoRetorno.ERRO);
            return ResponseEntity.badRequest().body(r);
        }
    }

	@ApiOperation(
			value="Apaga uma musica da base de dados", 
			notes="Essa operação apaga as informações de uma musica")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um ResponseEntity com uma mensagem de sucesso",
					response=Track.class
					),
			@ApiResponse(
					code=404, 
					message="Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
					response=Track.class
					)
 
	}) 	
    @DeleteMapping(value={"/deleta/{id_musica}"})
    public ResponseEntity<?> deletar(@PathVariable(value="id_musica") Integer id_musica)
    {
        Musica m;
        
        try
        {
            m = musicaService.getById(id_musica);
            if(m == null)
                return ResponseEntity.notFound().build();
        }
        catch(Exception e)
        {
        	Retorno r = new Retorno();
        	r.setCodigo("404");
        	r.setErro(e.getMessage());
        	r.setTipo(TipoRetorno.ERRO);
            return ResponseEntity.badRequest().body(r);
        }
        musicaService.delMusica(m);
        Retorno r = new Retorno();
    	r.setCodigo("200");
    	r.setErro("Excluída com sucesso");
    	r.setTipo(TipoRetorno.SUCESSO);
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }

    public void inclui_mysql(String album)
    {
        TrackReturn musicas = pesquisaiTunes(album);
      
        for(Track t: musicas.getResults()) {
        	
        	if (!t.getKind().equalsIgnoreCase("music-video")) {
	        	Musica m = new Musica();
	        		
	        	if (generoService.findByNome(t.getPrimaryGenreName()).isEmpty()) {
	        	    Genero g = new Genero();
	        	  	g.setNome(t.getPrimaryGenreName());
	        	  	generoService.addGenero(g);
	        	}  	
	        	m.setGenero(generoService.findByNome(t.getPrimaryGenreName()).get(0));
	        	
	        	if (albumService.findByNome(t.getCollectionName()).isEmpty()) {
	        	    Album al = new Album();
	        	  	al.setNome(t.getCollectionName());
	        	  	if (artistaService.getByArtista_Id(t.getArtistId())==null) {
	            		Artista artista = new Artista();
	            		artista.setArtista_id(t.getArtistId());
	            		artista.setNome(t.getArtistName());
	            		artista.setGenero(generoService.findByNome(t.getPrimaryGenreName()).get(0));
	            		artista.setUrl(t.getArtistViewUrl());
	            		artistaService.addArtista(artista);
	            	}
	        	  	al.setArtista(artistaService.getByArtista_Id(t.getArtistId()));
	        	  	al.setGenero(generoService.findByNome(t.getPrimaryGenreName()).get(0));
	        	  	al.setData_lancamento(t.getReleaseDate());
	        	  	al.setPreco(t.getCollectionPrice());
	        	  	al.setQtd_musicas(t.getTrackCount());
	        	  	al.setUrl(t.getCollectionViewUrl());
	        	  	albumService.addAlbum(al);
	        	}  	
	        	m.setAlbum(albumService.findByNome(t.getCollectionName()).get(0));
	        	
	        	m.setDataLancamento(t.getReleaseDate());
	        	m.setDuracao(t.getTrackTimeMillis());
	        	m.setIsStremable(t.getIsStreamable());
	        	m.setNome(t.getTrackName());
	        	m.setNumeroFaixa(t.getTrackNumber());
	        	m.setUrl(t.getPreviewUrl());
	        	m.setPreco(t.getTrackPrice());
	        	
	        	
	        	if (musicaService.getMusica(t.getTrackName()).isEmpty()) 
	        		musicaService.addMusica(m);
					
			}	
        }	

    }

    public TrackReturn pesquisaiTunes(String musica)
    {
    	TrackReturn t = new TrackReturn();
    	t.setResultCount(0);	
        try
        {
            RestTemplate restTemplate = new RestTemplate();
            String result = "";
            Gson gson = new Gson();
             if (!musica.equalsIgnoreCase("")){	
            	result = (String)restTemplate.getForObject(URL_ITUNES+musica, String.class);
            	TrackReturn track = (TrackReturn)gson.fromJson(result, TrackReturn.class);
            	if (track.getResultCount() > 0) {
                	return track;
            	}else {
                	return t;
                }
             }else {
             	return t;
             }
             
            
             /*}else if (!musica.equalsIgnoreCase("")){	
            	result = (String)restTemplate.getForObject(URL_ITUNES_MUSICA+musica, String.class);
            	TrackReturn artistas = (TrackReturn)gson.fromJson(result, TrackReturn.class);
            	return artistas;
            }else {
            	return a;
            }*/
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return t; 
        }
    }
    
	private List<Retorno> montaResposta(List<Musica> lista) {
		List<Retorno> listaRetorno = new ArrayList<Retorno>();

		for (Musica musica : lista) {
			Retorno r = new Retorno();
			r.setTipo(TipoRetorno.MUSICA);
			r.setCodigo("200");
			r.setEntity(musica);
			r.setErro("");
			listaRetorno.add(r);
			
		}

		return listaRetorno;
		
	}

   
}