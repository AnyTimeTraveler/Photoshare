package org.simonscode.photoshare.repositories;

import org.simonscode.photoshare.entities.Photo;
import org.simonscode.photoshare.entities.Tag;
import org.simonscode.photoshare.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findById(Long tagId);
    List<Tag> findAllByUserContaining(User user);
    List<Tag> findAllByPhotosContaining(Photo photo);
}