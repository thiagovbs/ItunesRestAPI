package com.desafio.stefanini.itunesrestclient.repository;

import com.desafio.stefanini.itunesrestclient.model.Artista;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Integer>
{

    @Query(value="select a from Artista a where (a.artistName like %?1% and a.collectionName like %?2% and a.trackName like %?3%)")
    public abstract List<Artista> buscarArtistas(String s, String s1, String s2);

    @Query(value="select a from Artista a where (a.artistId = ?1 or a.collectionName like ?2 or a.trackId = ?3)")
    public abstract Artista buscarArtistaPorId(Integer integer, String s, Integer integer1);

    public abstract List<Artista> findByArtistName(String s);

    public abstract List<Artista> findByCollectionName(String s);

    public abstract List<Artista> findByTrackName(String s);

    public abstract Artista findByArtistId(Integer integer);
}
