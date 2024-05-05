package com.example.bookingticket.service;

import com.example.bookingticket.common.constant.Status;
import com.example.bookingticket.common.util.ResponseUtils;
import com.example.bookingticket.exception.NotFoundException;
import com.example.bookingticket.model.dto.request.GenreRequest;
import com.example.bookingticket.model.dto.response.GenreDTO;
import com.example.bookingticket.model.dto.response.ResponseDTO;
import com.example.bookingticket.model.entity.Genre;
import com.example.bookingticket.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public ResponseDTO getAccountDetailById(Integer id) {
        log.info("----- Get genre Start------");
        try {
            Genre genre = genreRepository.findById(id)
                    .orElseThrow(()->new NotFoundException("genre with id "+ id));

            log.info("----- Get genre End ------");
            return ResponseUtils.success(genre, "Get genre success");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Get genre fail");
        }
    }

    public ResponseDTO getAllGenre() {
        log.info("----- Get list genre Start------");
        try {
            List<Genre> genre = genreRepository.findAllGenre();
            log.info("----- Get list genre End ------");
            return ResponseUtils.success(genre, "Get list genre success");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Get list genre fail");
        }
    }

    public ResponseDTO getActiveGenre() {
        try {
            List<Genre> genre = genreRepository.findGenreWithStatus(Status.ACTIVATE);
            return ResponseUtils.success(genre, "Get list active genre success");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Get list active genre fail");
        }
    }

    public ResponseDTO createUpdateGenre(GenreRequest genreRequest) {
        try{
            Genre genre = new Genre();
            if (genreRequest.getId() != 0){
                genre = genreRepository.getById(genreRequest.getId());
            }
            if (genreRepository.findByCode(genreRequest.getCode())!=null
            && !genre.getCode().equals(genreRequest.getCode())
            ){
                return ResponseUtils.error("Code genre đã tồn tại");
            }
            genre.setCode(genreRequest.getCode() == null ? genre.getCode() : genreRequest.getCode());
            genre.setName(genreRequest.getName() == null ? genre.getName() : genreRequest.getName());
            genre.setDescription(genreRequest.getDescription() == null ? genre.getDescription() : genreRequest.getDescription());
            genre.setStatus(genreRequest.getStatus());
            genreRepository.save(genre);
            return ResponseUtils.success(genre.getId(), "Lưu thành công");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseUtils.error("Get update and create genre fail");
        }
    }

    public ResponseDTO deleteGenre(int id) {
        try {
            Genre genre = genreRepository.getById(id);
            if (genre == null){
                return ResponseUtils.error("genre ko tồn tại");
            }
            genre.setStatus(Status.DELETED);
            genreRepository.save(genre);
            return ResponseUtils.success(genre, "Delete genre success");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Delete genre fail");
        }
    }
}
