package com.leonardo.animepoll.repositories;

import com.leonardo.animepoll.models.Anime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long>{
    
}
