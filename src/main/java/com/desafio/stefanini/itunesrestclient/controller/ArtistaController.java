package com.desafio.stefanini.itunesrestclient.controller;

import com.desafio.stefanini.itunesrestclient.model.api.Artista;
import com.desafio.stefanini.itunesrestclient.model.api.Genero;
import com.desafio.stefanini.itunesrestclient.model.api.Retorno;
import com.desafio.stefanini.itunesrestclient.model.api.TipoRetorno;
import com.desafio.stefanini.itunesrestclient.model.itunes.Artist;
import com.desafio.stefanini.itunesrestclient.model.itunes.ArtistReturn;
import com.desafio.stefanini.itunesrestclient.model.itunes.Track;
import com.desafio.stefanini.itunesrestclient.service.ArtistaService;
import com.desafio.stefanini.itunesrestclient.service.GeneroService;
import com.google.gson.Gson;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

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
@RequestMapping(value={"/artista"})
public class ArtistaController
{
	
	static final String URL_ITUNES = "https://itunes.apple.com/search?country=br&media=music&limit=200&entity=musicArtist&term=";
	
	
	@Autowired
	private ArtistaService artistaService;

	@Autowired
	private GeneroService generoService;	

    public ArtistaController()
    {
    }

    
	@ApiOperation(
			value="Lista informações sobre artistas com o termo informado", 
			notes="Essa operação lista informações sobre artistas com o termo informado")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma lista contendo os artistas com o nome informado",
					response=Track.class
					),
			@ApiResponse(
					code=404, 
					message="Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
					response=Track.class
					)
 
	})    
    @RequestMapping(method=RequestMethod.GET, value={"/lista", "/lista/{artista}"})
	public ResponseEntity<?> listar(@PathVariable(value="artista", required = false) String artista)
    {
		if (artista != null) {
        	return pesquisaArtista(artista);
        }else {
        	Retorno r = new Retorno();
        	r.setCodigo("404");
        	r.setErro("É obrigatório informar um termo a ser pesquisado");
        	r.setTipo(TipoRetorno.ERRO);
        	return ResponseEntity.badRequest().body(r);
        } 
    }
	
	@ApiOperation(
			value="Insere um artista na base de dados", 
			response=Track.class, 
			notes="Essa operação insere informações de um artista")
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
    @PutMapping(value={"/insere"})
    public ResponseEntity<?> inserir(@RequestBody Artista artista)
    {
        try
        {
            artistaService.addArtista(artista);
            return ResponseEntity.status(HttpStatus.OK).body("Incluído com sucesso");
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
			value="Altera um artista na base de dados", 
			response=Track.class, 
			notes="Essa operação altera informações de um artista")
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
    @PatchMapping(value={"/altera"})
    public ResponseEntity<?> alterar(@RequestBody Artista artista)
    {
        try
        {
            artistaService.editArtista(artista);
            return ResponseEntity.status(HttpStatus.OK).body("Alterado com sucesso");
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
    @DeleteMapping(value={"/deleta"})
    public ResponseEntity<?> deletar(@PathParam(value="id_artista") Integer id_artista, @PathParam(value="album") String album, @PathParam(value="id_musica") Integer id_musica)
    {
        Artista a;
        
        try
        {
            a = artistaService.getById(id_artista);
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
        artistaService.delArtista(a);
        Retorno r = new Retorno();
    	r.setCodigo("200");
    	r.setErro("Excluído com sucesso");
    	r.setTipo(TipoRetorno.SUCESSO);
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).body(r);
    }

    public void inclui_mysql(String artista)
    {
        ArtistReturn artistas = pesquisaiTunes(artista);
      
        for(Artist a: artistas.getResults()) {
        	Artista art = new Artista();
        	art.setArtista_id(a.getArtistId());
        	if (generoService.findByNome(a.getPrimaryGenreName()).isEmpty()) {
        	    Genero g = new Genero();
        	  	g.setNome(a.getPrimaryGenreName());
        	  	generoService.addGenero(g);
        	  	art.setGenero(generoService.findByNome(a.getPrimaryGenreName()).get(0));
        	}else {
        		art.setGenero(generoService.findByNome(a.getPrimaryGenreName()).get(0));
        	}  	
        	
        	art.setNome(a.getArtistName());
        	art.setUrl(a.getArtistLinkUrl());
        	
        	if (artistaService.getByArtista_Id(a.getArtistId()) == null) 
        		try {
        			artistaService.addArtista(art);
				} catch (Exception e) {
					// não faz nada, evita o erro javax.persistence.EntityExistsException. Procurar a maneira certa de resolver este problema!
				}
        }	

    }

    public ArtistReturn pesquisaiTunes(String artista)
    {
    	ArtistReturn a = new ArtistReturn();
    	a.setResultCount(0);	
        try
        {
            RestTemplate restTemplate = new RestTemplate();
            String result = "";
            Gson gson = new Gson();
            if (!artista.equalsIgnoreCase("")){
            	result = (String)restTemplate.getForObject(URL_ITUNES+artista, String.class);
            	ArtistReturn ar = (ArtistReturn)gson.fromJson(result, ArtistReturn.class);
            	if (ar.getResultCount() > 0) {
            		return ar;
            	}else {
                	return a;
                }
            }else {
            	return a;
            }	
            /*}else if (!album.equalsIgnoreCase("")){	
            	result = (String)restTemplate.getForObject(URL_ITUNES_ALBUM+album, String.class);
            	CollectionReturn b = (CollectionReturn)gson.fromJson(result, CollectionReturn.class);
            	if (b.getResultCount() > 0) {
            		result = (String)restTemplate.getForObject(URL_ITUNES_MUSICA+b.getResults().get(0).getArtistName(), String.class);
                	TrackReturn artistas = (TrackReturn)gson.fromJson(result, TrackReturn.class);
                	return artistas;
            	}else {
                	return a;
                }	
            }else if (!musica.equalsIgnoreCase("")){	
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
            return a; 
        }
    }
    
    private ResponseEntity<?> pesquisaArtista(String artista){
		List<Artista> a = artistaService.getArtista(artista);
        try
        {
            if(a.isEmpty())
            {
                inclui_mysql(artista);
                a = artistaService.getArtista(artista);
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
    
	private List<Retorno> montaResposta(List<Artista> lista) {
		List<Retorno> listaRetorno = new ArrayList<Retorno>();

		for (Artista artista : lista) {
			Retorno r = new Retorno();
			r.setTipo(TipoRetorno.ARTISTA);
			r.setCodigo("200");
			r.setEntity(artista);
			r.setErro("");
			listaRetorno.add(r);
			
		}

		return listaRetorno;
		
	}

   
}