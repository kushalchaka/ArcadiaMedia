package com.arcadia.repository;

import com.arcadia.model.MediaItem;
import com.arcadia.model.MediaType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface MediaItemRepository extends JpaRepository<MediaItem, Long> {
    Optional<MediaItem> findByExternalIdAndExternalSource(String externalId, String externalSource);
    Page<MediaItem> findByMediaType(MediaType mediaType, Pageable pageable);

    @Query("""
        SELECT DISTINCT m FROM MediaItem m
        JOIN m.genres g
        WHERE LOWER(g.name) IN :genreNames
    """)
    Page<MediaItem> findByGenreNames(@Param("genreNames") Collection<String> genreNames, Pageable pageable);
}
