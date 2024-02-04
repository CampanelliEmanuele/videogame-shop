package org.learning.videogameshop.api;

import org.learning.videogameshop.model.Videogame;
import org.learning.videogameshop.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/videogames")
public class RestVideogameController {
    @Autowired
    private VideogameRepository videogameRepository;

    @GetMapping("allVideogames")
    public List<Videogame> allVideogames() {
        List<Videogame> videogameList = videogameRepository.findAll();
        return videogameList;
    }

    @GetMapping("availableVideogames")
    public List<Videogame> availableVideogames() {
        List<Videogame> videogameList = videogameRepository.findAll();
        videogameList.removeIf(videogame -> videogame.getAvailableCopies() <= 0);
        return videogameList;
    }
}

