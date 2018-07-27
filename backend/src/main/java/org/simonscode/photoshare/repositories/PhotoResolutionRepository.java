package org.simonscode.photoshare.repositories;

import org.simonscode.photoshare.entities.PhotoResolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoResolutionRepository extends JpaRepository<PhotoResolution, Long> {
    Optional<PhotoResolution> findById(Long tagId);
}