package org.simonscode.photoshare.repositories;

import org.simonscode.photoshare.entities.Tag;
import org.simonscode.photoshare.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long tagId);
    User findByEmail(String email);
    User findByUsername(String username);
    List<User> findAllByTagsContaining(Tag tag);
    User findUserByUsernameAndPasswordHash(String username, String passwordHash);
}