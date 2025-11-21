package com.mege.webseer.repositories;

import com.mege.webseer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT * FROM users WHERE name = :name AND password = :password", nativeQuery = true)
    Optional<User> loginUser(@Param("name") String name, @Param("password") String password);
}
