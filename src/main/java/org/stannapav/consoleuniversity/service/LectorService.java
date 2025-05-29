package org.stannapav.consoleuniversity.service;

import org.springframework.stereotype.Service;
import org.stannapav.consoleuniversity.db.entities.Lector;
import org.stannapav.consoleuniversity.db.repositories.LectorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectorService {
    final private LectorRepository lectorRepository;

    public LectorService(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }


    public String globalSearch(String keyword) {
        List<Lector> matches = lectorRepository.findByNameContainsIgnoreCase(keyword);
        
        if(matches.isEmpty()){
            return "Lectors not found";
        }

        return matches.stream()
                .map(Lector::getName)
                .collect(Collectors.joining(", "));
    }
}
