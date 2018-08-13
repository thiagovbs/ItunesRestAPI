package com.desafio.stefanini.itunesrestclient.controller;

import com.desafio.stefanini.itunesrestclient.model.AlbumReturn;
import com.desafio.stefanini.itunesrestclient.model.ArtistaReturn;
import com.desafio.stefanini.itunesrestclient.model.MusicaReturn;
import com.desafio.stefanini.itunesrestclient.model.Musica;
import com.desafio.stefanini.itunesrestclient.service.ArtistaService;
import com.google.gson.Gson;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping(value={"/artista"})
public class ArtistaController
{

    public ArtistaController()
    {
    }

    
	@ApiOperation(
			value="Listar artistas, albuns e musicas", 
			notes="Essa operação lista artistas, albuns e músicas")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um ResponseEntity com uma mensagem de sucesso",
					response=Musica.class
					),
			@ApiResponse(
					code=400, 
					message="Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
					response=Musica.class
					)
 
	})    
    @GetMapping(value={"/listar"})
    public ResponseEntity<?> listar(@RequestParam(value="artista", defaultValue="") String artista, @RequestParam(value="album", defaultValue="") String album, @RequestParam(value="musica", defaultValue="") String musica)
    {
        List<Musica> a = artistaService.buscarArtistas(artista, album, musica);
        try
        {
            if(a.isEmpty())
            {
                inclui_mysql(artista,album,musica);
                a = artistaService.buscarArtistas(artista, album, musica);
            }
            return ResponseEntity.status(HttpStatus.OK).body(a);
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

	@ApiOperation(
			value="Insere um artista na base de dados", 
			response=Musica.class, 
			notes="Essa operação insere informações de um artista")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um ResponseEntity com uma mensagem de sucesso",
					response=Musica.class
					),
			@ApiResponse(
					code=400, 
					message="Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
					response=Musica.class
					)
 
	})  	
    @PutMapping(value={"/inserir"})
    public ResponseEntity<?> inserir(@RequestBody Musica artista)
    {
        try
        {
            artistaService.addArtista(artista);
            return ResponseEntity.status(HttpStatus.OK).body("Incluído com sucesso");
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

	@ApiOperation(
			value="Altera um artista na base de dados", 
			response=Musica.class, 
			notes="Essa operação altera informações de um artista")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um ResponseEntity com uma mensagem de sucesso",
					response=Musica.class
					),
			@ApiResponse(
					code=400, 
					message="Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
					response=Musica.class
					)
 
	}) 	
    @PostMapping(value={"/alterar"})
    public ResponseEntity<?> alterar(@RequestBody Musica artista)
    {
        try
        {
            artistaService.editArtista(artista);
            return ResponseEntity.status(HttpStatus.OK).body("Alterado com sucesso");
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

	@ApiOperation(
			value="Apaga um artista na base de dados", 
			notes="Essa operação apaga as informações de um artista")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um ResponseEntity com uma mensagem de sucesso",
					response=Musica.class
					),
			@ApiResponse(
					code=400, 
					message="Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
					response=Musica.class
					)
 
	}) 	
    @DeleteMapping(value={"/deletar"})
    public ResponseEntity<?> deletar(@RequestParam(value="id_artista", defaultValue="") Integer id_artista, @RequestParam(value="album", defaultValue="") String album, @RequestParam(value="id_musica", defaultValue="") Integer id_musica)
    {
        Musica a;
        try
        {
            a = artistaService.buscarArtistaPorId(id_artista, album, id_musica);
            if(a == null)
                return ResponseEntity.notFound().build();
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        artistaService.delArtista(a);
        return ResponseEntity.status(HttpStatus.OK).body("Excluído com sucesso");
    }

    public void inclui_mysql(String artista, String album, String musica)
    {
        MusicaReturn artistas = pesquisaiTunes(artista, album, musica);
      
        for(Musica m: artistas.getResults()) {
        	artistaService.addArtista(m);
        }	

    }

    public MusicaReturn pesquisaiTunes(String artista, String album, String musica)
    {
    	MusicaReturn a = new MusicaReturn();
    	a.setResultCount(0);	
        try
        {
            RestTemplate restTemplate = new RestTemplate();
            String result = "";
            Gson gson = new Gson();
            if (!artista.equalsIgnoreCase("")){
            	result = (String)restTemplate.getForObject(URL_ITUNES_ARTISTA+artista, String.class);
            	ArtistaReturn ar = (ArtistaReturn)gson.fromJson(result, ArtistaReturn.class);
            	if (ar.getResultCount() > 0) {
            		result = (String)restTemplate.getForObject(URL_ITUNES_MUSICA+ar.getResults().get(0).getArtistName(), String.class);
                	MusicaReturn artistas = (MusicaReturn)gson.fromJson(result, MusicaReturn.class);
                	return artistas;
            	}else {
                	return a;
                }	
            }else if (!album.equalsIgnoreCase("")){	
            	result = (String)restTemplate.getForObject(URL_ITUNES_ALBUM+album, String.class);
            	AlbumReturn b = (AlbumReturn)gson.fromJson(result, AlbumReturn.class);
            	if (b.getResultCount() > 0) {
            		result = (String)restTemplate.getForObject(URL_ITUNES_MUSICA+b.getResults().get(0).getArtistName(), String.class);
                	MusicaReturn artistas = (MusicaReturn)gson.fromJson(result, MusicaReturn.class);
                	return artistas;
            	}else {
                	return a;
                }	
            }else if (!musica.equalsIgnoreCase("")){	
            	result = (String)restTemplate.getForObject(URL_ITUNES_MUSICA+musica, String.class);
            	MusicaReturn artistas = (MusicaReturn)gson.fromJson(result, MusicaReturn.class);
            	return artistas;
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

    static final String URL_ITUNES_ARTISTA = "https://itunes.apple.com/search?country=br&media=music&limit=200&entity=musicArtist&term=";
    static final String URL_ITUNES_ALBUM = "https://itunes.apple.com/search?country=br&media=music&limit=200&entity=album&term=";
    static final String URL_ITUNES_MUSICA = "https://itunes.apple.com/search?country=br&media=music&limit=200&entity=musicTrack&term=";
    @Autowired
    private ArtistaService artistaService;
}