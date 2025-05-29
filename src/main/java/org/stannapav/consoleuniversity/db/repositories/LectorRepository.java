package org.stannapav.consoleuniversity.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.stannapav.consoleuniversity.db.entities.Lector;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {
    List<Lector> findByNameContainsIgnoreCase(String name);
}
