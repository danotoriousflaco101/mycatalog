package com.flaco.mycatalog.repository;

import com.flaco.mycatalog.model.MediaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaItemRepository extends JpaRepository<MediaItem, Long> {
    List<MediaItem> findAllByOrderByAddedDateDesc();
}