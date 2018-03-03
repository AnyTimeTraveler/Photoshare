package org.simonscode.photoshare.repositories;

import org.simonscode.photoshare.entities.Photo;
import org.simonscode.photoshare.entities.Tag;
import org.simonscode.photoshare.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findById(Long tagId);

    Set<Tag> findAllByIdIn(List<Long> tagIds);
    List<Tag> findAllByUserContaining(User user);
    List<Tag> findAllByPhotosContaining(Photo photo);
}