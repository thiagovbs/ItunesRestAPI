package com.desafio.stefanini.itunesrestclient.repository;

import com.desafio.stefanini.itunesrestclient.model.Musica;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistaRepository extends JpaRepository<Musica, Integer>
{

    @Query(value="select a from Musica a where (a.artistName like %?1% and a.collectionName like %?2% and a.trackName like %?3%)")
    public abstract List<Musica> buscarArtistas(String s, String s1, String s2);

    @Query(value="select a from Musica a where (a.artistId = ?1 or a.collectionName like ?2 or a.trackId = ?3)")
    public abstract Musica buscarArtistaPorId(Integer integer, String s, Integer integer1);

    public abstract List<Musica> findByArtistName(String s);

    public abstract List<Musica> findByCollectionName(String s);

    public abstract List<Musica> findByTrackName(String s);

    public abstract Musica findByArtistId(Integer integer);
}
