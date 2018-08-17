package com.desafio.stefanini.itunesrestclient.controller;

import com.desafio.stefanini.itunesrestclient.model.api.Album;
import com.desafio.stefanini.itunesrestclient.model.api.Artista;
import com.desafio.stefanini.itunesrestclient.model.api.Genero;
import com.desafio.stefanini.itunesrestclient.model.api.Retorno;
import com.desafio.stefanini.itunesrestclient.model.api.TipoRetorno;
import com.desafio.stefanini.itunesrestclient.model.itunes.Collection;
import com.desafio.stefanini.itunesrestclient.model.itunes.CollectionReturn;
import com.desafio.stefanini.itunesrestclient.model.itunes.Track;
import com.desafio.stefanini.itunesrestclient.service.AlbumService;
import com.desafio.stefanini.itunesrestclient.service.ArtistaService;
import com.desafio.stefanini.itunesrestclient.service.GeneroService;
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
public class AlbumController
{
	

	 static final String URL_ITUNES = "https://itunes.apple.com/search?country=br&media=music&limit=200&entity=album&term=";

	 @Autowired
	 private AlbumService albumService;
	 
	 @Autowired
	 private ArtistaService artistaService;	 
	 
	 @Autowired
	 private GeneroService generoService;	
	

    public AlbumController()
    {
    }

    
	@ApiOperation(
			value="Lista informações sobre albuns com o termo informado", 
			notes="Essa operação lista informações sobre albuns com o termo informado")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma lista contendo os albuns com o termo informado",
					response=Retorno.class
					),
			@ApiResponse(
					code=404, 
					message="Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
					response=Retorno.class
					)
 
	})    
    @RequestMapping(method=RequestMethod.GET, value={"/album", "/album/{album}"})
	public ResponseEntity<?> listar(@PathVariable(value="album", required = false) String album)
    {
        if (album != null) {
        	return pesquisaAlbum(album);
        }else {
        	return pesquisaAlbum("");
        } 
    }
	
	private ResponseEntity<?> pesquisaAlbum(String album){
		List<Album> a = albumService.findByNome(album);
        try
        {
            if(a.isEmpty())
            {
                inclui_mysql(album);
                a = albumService.findByNome(album);
            }
            return ResponseEntity.status(HttpStatus.OK).body( montaResposta(a));
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
			value="Insere um album na base de dados", 
			response=Album.class, 
			notes="Essa operação insere informações de um album")
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
    @PutMapping(value={"/album"})
    public ResponseEntity<?> inserir(@RequestBody Album album)
    {
        try
        {
        	
        	if (generoService.findByNome(album.getGenero().getNome()).isEmpty()) {
        		Genero g = new Genero();
        		g.setNome(album.getGenero().getNome());
        	  	generoService.addGenero(g);
        	}  	
        	album.setGenero(generoService.findByNome(album.getGenero().getNome()).get(0));
        	
        	if (artistaService.getByArtista_Id(album.getArtista().getArtista_id())==null) {
        		Artista artista = new Artista();
        		artista.setArtista_id(album.getArtista().getArtista_id());
        		artista.setNome(album.getArtista().getNome());
        		artista.setGenero(generoService.findByNome(album.getGenero().getNome()).get(0));
        		artista.setUrl(album.getArtista().getUrl());
        		artistaService.addArtista(artista);
        	}
        	album.setArtista(artistaService.getByArtista_Id(album.getArtista().getArtista_id()));
        	
        	albumService.addAlbum(album);
        	Retorno r = new Retorno();
         	r.setCodigo("200");
         	r.setErro("");
         	r.setEntity(album);
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
			value="Altera um album na base de dados", 
			response=Album.class, 
			notes="Essa operação altera informações de um album")
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
    @PatchMapping(value={"/album"})
    public ResponseEntity<?> alterar(@RequestBody Album album)
    {
        try
        {
        	if (generoService.findByNome(album.getGenero().getNome()).isEmpty()) {
        		Genero g = new Genero();
        		g.setNome(album.getGenero().getNome());
        	  	generoService.addGenero(g);
        	}  	
        	album.setGenero(generoService.findByNome(album.getGenero().getNome()).get(0));
        	
        	if (artistaService.getByArtista_Id(album.getArtista().getArtista_id())==null) {
        		Artista artista = new Artista();
        		artista.setArtista_id(album.getArtista().getArtista_id());
        		artista.setNome(album.getArtista().getNome());
        		artista.setGenero(generoService.findByNome(album.getGenero().getNome()).get(0));
        		artista.setUrl(album.getArtista().getUrl());
        		artistaService.addArtista(artista);
        	}
        	album.setArtista(artistaService.getByArtista_Id(album.getArtista().getArtista_id()));
        	
        	albumService. editAlbum(album);
        	Retorno r = new Retorno();
         	r.setCodigo("200");
         	r.setErro("");
         	r.setEntity(album);
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
			value="Apaga um artista na base de dados", 
			notes="Essa operação apaga as informações de um artista")
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
    @DeleteMapping(value={"/album/{id_album}"})
    public ResponseEntity<?> deletar(@PathVariable(value="id_album") Integer id_album)
    {
        Album a;
        
        try
        {
            a = albumService.getById(id_album);
            if(a == null)
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
        albumService.delAlbum(a);
        Retorno r = new Retorno();
    	r.setCodigo("200");
    	r.setErro("");
    	r.setTipo(TipoRetorno.SUCESSO);
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }

    public void inclui_mysql(String album)
    {
        CollectionReturn albuns = pesquisaiTunes(album);
      
        for(Collection c: albuns.getResults()) {
        	Album a = new Album();
        		
        	if (generoService.findByNome(c.getPrimaryGenreName()).isEmpty()) {
        	    Genero g = new Genero();
        	  	g.setNome(c.getPrimaryGenreName());
        	  	generoService.addGenero(g);
        	}  	
        	a.setGenero(generoService.findByNome(c.getPrimaryGenreName()).get(0));
        	
        	if (artistaService.getByArtista_Id(c.getArtistId())==null) {
        		Artista artista = new Artista();
        		artista.setArtista_id(c.getArtistId());
        		artista.setNome(c.getArtistName());
        		artista.setGenero(generoService.findByNome(c.getPrimaryGenreName()).get(0));
        		artista.setUrl(c.getArtistViewUrl());
        		artistaService.addArtista(artista);
        	}
    	  	a.setArtista(artistaService.getByArtista_Id(c.getArtistId()));
           		
        	
        	
        	a.setNome(c.getCollectionName());
        	a.setUrl(c.getCollectionViewUrl());
        	a.setPreco(c.getCollectionPrice());
        	a.setQtd_musicas(c.getTrackCount());
        	a.setData_lancamento(c.getReleaseDate());
        	
        	if (albumService.findByNome(c.getCollectionName()).isEmpty()) 
        		albumService.addAlbum(a);
        }	

    }

    public CollectionReturn pesquisaiTunes(String album)
    {
    	CollectionReturn a = new CollectionReturn();
    	a.setResultCount(0);	
        try
        {
            RestTemplate restTemplate = new RestTemplate();
            String result = "";
            Gson gson = new Gson();
             if (!album.equalsIgnoreCase("")){	
            	result = (String)restTemplate.getForObject(URL_ITUNES+album, String.class);
            	CollectionReturn b = (CollectionReturn)gson.fromJson(result, CollectionReturn.class);
            	if (b.getResultCount() > 0) {
                	return b;
            	}else {
                	return a;
                }
             }else {
             	return a;
             }
          
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return a; 
        }
    }
    
	private List<Retorno> montaResposta(List<Album> lista) {
		List<Retorno> listaRetorno = new ArrayList<Retorno>();

		for (Album album : lista) {
			Retorno r = new Retorno();
			r.setTipo(TipoRetorno.ALBUM);
			r.setCodigo("200");
			r.setEntity(album);
			r.setErro("");
			listaRetorno.add(r);
			
		}

		return listaRetorno;
		
	}

   
}