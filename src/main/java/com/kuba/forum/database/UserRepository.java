package com.kuba.forum.database;

import com.kuba.forum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(final int userId);

    Optional<User> findByLogin(final String login);
}
