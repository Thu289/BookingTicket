package com.example.bookingticket.controller;

import com.example.bookingticket.model.dto.request.GenreRequest;
import com.example.bookingticket.model.dto.response.ResponseDTO;
import com.example.bookingticket.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/{id}")
    public ResponseDTO getGenreDetail(@PathVariable Integer id){
        return genreService.getAccountDetailById(id);
    }

    @GetMapping("/{all}")
    public ResponseDTO getAllGenre(){
        return genreService.getAllGenre();
    }

    @GetMapping("/{active}")
    public ResponseDTO getAllActiveGenre(){
        return genreService.getActiveGenre();
    }
    @PostMapping()
    public ResponseDTO createUpdateGenre(@RequestBody GenreRequest genreRequest) {
        return genreService.createUpdateGenre(genreRequest);
    }

    @PutMapping("/{id}")
    public ResponseDTO deleteGenre(@PathVariable int id){
        return genreService.deleteGenre(id);
    }
}
