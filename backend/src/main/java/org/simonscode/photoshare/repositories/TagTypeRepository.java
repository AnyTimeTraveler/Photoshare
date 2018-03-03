package org.simonscode.photoshare.repositories;

import org.simonscode.photoshare.entities.TagType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagTypeRepository extends JpaRepository<TagType, Long> {
    Optional<TagType> findById(Long tagId);

    List<TagType> findAllByNameContaining(String name);
}