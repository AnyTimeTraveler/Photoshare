package org.simonscode.photoshare.repositories;

import org.simonscode.photoshare.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Optional<Photo> findById(Long tagId);
    @Query(value = "SELECT p from Photo p JOIN p.tags t WHERE t.id = :tagId")
    List<Photo> findAllByTagID(@Param("tagId") Long tagId);
}