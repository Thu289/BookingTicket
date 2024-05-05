package com.example.bookingticket.service;

import com.example.bookingticket.common.constant.Status;
import com.example.bookingticket.common.util.ResponseUtils;
import com.example.bookingticket.exception.NotFoundException;
import com.example.bookingticket.model.dto.request.CinemaRequest;
import com.example.bookingticket.model.dto.response.ResponseDTO;
import com.example.bookingticket.model.entity.Cinema;
import com.example.bookingticket.model.entity.Genre;
import com.example.bookingticket.repository.CinemaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;

    public ResponseDTO getCinemaDetailById(Integer id) {
        try {
            Cinema cinema = cinemaRepository.findById(id)
                    .orElseThrow(()->new NotFoundException("cinema with id "+ id));
            return ResponseUtils.success(cinema, "Get cinema success");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Get cinema fail");
        }
    }

    public ResponseDTO getAllCinema() {
        try {
            List<Cinema> cinemas = cinemaRepository.findAllCinema();
            return ResponseUtils.success(cinemas, "Get list cinema success");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Get list cinema fail");
        }
    }

    public ResponseDTO getActiveCinema() {
        try {
            List<Cinema> cinemas = cinemaRepository.findCinemaWithStatus(Status.ACTIVATE);
            return ResponseUtils.success(cinemas, "Get list active cinema success");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Get list active cinema fail");
        }
    }

    public ResponseDTO deleteCinema(int id) {
        try {
            Cinema cinema = cinemaRepository.getById(id);
            if (cinema == null){
                return ResponseUtils.error("cinema ko tồn tại");
            }
            cinema.setStatus(Status.DELETED);
            cinemaRepository.save(cinema);
            return ResponseUtils.success(cinema, "Delete cinema success");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Delete cinema fail");
        }
    }

    public ResponseDTO createUpdateCinema(CinemaRequest cinemaRequest) {
        try{
            Cinema cinema = new Cinema();
            if (cinemaRequest.getId() != 0){
                cinema = cinemaRepository.getById(cinemaRequest.getId());
            }
            if (cinemaRepository.findByCode(cinemaRequest.getCode())!=null
                    && !cinema.getCode().equals(cinemaRequest.getCode())
            ){
                return ResponseUtils.error("Code cinema đã tồn tại");
            }
            cinema.setCode(cinemaRequest.getCode() == null ? cinema.getCode() : cinemaRequest.getCode());
            cinema.setName(cinemaRequest.getName() == null ? cinema.getName() : cinemaRequest.getName());
            cinema.setAddress(cinemaRequest.getAddress() == null ? cinema.getAddress() : cinemaRequest.getAddress());
            cinema.setStatus(cinemaRequest.getStatus());
            cinemaRepository.save(cinema);
            return ResponseUtils.success(cinema.getId(), "Lưu thành công");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseUtils.error("Get update and create cinema fail");
        }
    }
}
