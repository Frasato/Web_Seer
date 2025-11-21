package com.mege.webseer.repositories;

import com.mege.webseer.models.Web;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface WebRepository extends JpaRepository<Web, String> {
    @Query(value = "SELECT * FROM webs WHERE user_id = :id", nativeQuery = true)
    Optional<List<Web>> findAllWebByUserId(@Param("id") String id);

    @Query(value = "SELECT * FROM webs WHERE url = :url", nativeQuery = true)
    Optional<Web> findByUrl(@Param("url") String url);
}
