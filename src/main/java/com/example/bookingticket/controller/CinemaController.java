package com.example.bookingticket.controller;

import com.example.bookingticket.model.dto.request.CinemaRequest;
import com.example.bookingticket.model.dto.request.GenreRequest;
import com.example.bookingticket.model.dto.response.ResponseDTO;
import com.example.bookingticket.model.entity.Cinema;
import com.example.bookingticket.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cinema")
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;

    @GetMapping("/{id}")
    public ResponseDTO getGenreDetail(@PathVariable Integer id){
        return cinemaService.getCinemaDetailById(id);
    }

    @GetMapping("/{all}")
    public ResponseDTO getAllGenre(){
        return cinemaService.getAllCinema();
    }

    @GetMapping("/{active}")
    public ResponseDTO getAllActiveGenre(){
        return cinemaService.getActiveCinema();
    }
    @PostMapping()
    public ResponseDTO createUpdateGenre(@RequestBody CinemaRequest cinemaRequest) {
        return cinemaService.createUpdateCinema(cinemaRequest);
    }

    @PutMapping("/{id}")
    public ResponseDTO deleteCinema(@PathVariable int id){
        return cinemaService.deleteCinema(id);
    }
}
