package com.desafio.stefanini.itunesrestclient.controller;

import com.desafio.stefanini.itunesrestclient.model.Artista;
import com.desafio.stefanini.itunesrestclient.model.ArtistsReturn;
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
			response=Artista.class, 
			notes="Essa operação lista artistas, albuns e músicas")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um ResponseEntity com uma mensagem de sucesso",
					response=Artista.class
					),
			@ApiResponse(
					code=500, 
					message="Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
					response=Artista.class
					)
 
	})    
    @GetMapping(value={"/listar"})
    public ResponseEntity<?> listar(@RequestParam(value="artista", defaultValue="") String artista, @RequestParam(value="album", defaultValue="") String album, @RequestParam(value="musica", defaultValue="") String musica)
    {
        List<Artista> a = artistaService.buscarArtistas(artista, album, musica);
        try
        {
            if(a.isEmpty())
            {
                inclui_mysql(artista);
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
			response=Artista.class, 
			notes="Essa operação insere informações de um artista")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um ResponseEntity com uma mensagem de sucesso",
					response=Artista.class
					),
			@ApiResponse(
					code=500, 
					message="Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
					response=Artista.class
					)
 
	})  	
    @PutMapping(value={"/inserir"})
    public ResponseEntity<?> inserir(@RequestBody Artista artista)
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
			response=Artista.class, 
			notes="Essa operação altera informações de um artista")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um ResponseEntity com uma mensagem de sucesso",
					response=Artista.class
					),
			@ApiResponse(
					code=500, 
					message="Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
					response=Artista.class
					)
 
	}) 	
    @PostMapping(value={"/alterar"})
    public ResponseEntity<?> alterar(@RequestBody Artista artista)
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
			response=Artista.class, 
			notes="Essa operação apaga as informações de um artista")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um ResponseEntity com uma mensagem de sucesso",
					response=Artista.class
					),
			@ApiResponse(
					code=500, 
					message="Caso tenhamos algum erro vamos retornar um ResponseEntity com a Exception",
					response=Artista.class
					)
 
	}) 	
    @DeleteMapping(value={"/deletar"})
    public ResponseEntity<?> deletar(@RequestParam(value="id_artista", defaultValue="") Integer id_artista, @RequestParam(value="album", defaultValue="") String album, @RequestParam(value="id_musica", defaultValue="") Integer id_musica)
    {
        Artista a;
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
        return ResponseEntity.status(HttpStatus.OK).body("Exclu\355do com sucesso");
    }

    public void inclui_mysql(String artista)
    {
        String retorno = pesquisaiTunes(artista, "", "");
        Gson gson = new Gson();
        ArtistsReturn artistas = (ArtistsReturn)gson.fromJson(retorno, ArtistsReturn.class);
      
        for(Artista a: artistas.getResults()) {
        	artistaService.addArtista(a);
        }	

    }

    public String pesquisaiTunes(String artista, String album, String musica)
    {
        try
        {
            RestTemplate restTemplate = new RestTemplate();
            String result = (String)restTemplate.getForObject("https://itunes.apple.com/search?country=br&media=music&limit=200&entity=musicTrack&term="+artista, String.class);
            return result;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    static final String URL_ITUNES = "https://itunes.apple.com/search?country=br&media=music&limit=200&entity=musicTrack&term=";
    @Autowired
    private ArtistaService artistaService;
}