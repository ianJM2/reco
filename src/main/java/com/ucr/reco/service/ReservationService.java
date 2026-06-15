package com.ucr.reco.service;

import com.ucr.reco.model.Reservation;
import com.ucr.reco.model.Space;
import com.ucr.reco.model.StatusValores;
import com.ucr.reco.model.User;
import com.ucr.reco.model.dto.ReservationDTO;
import com.ucr.reco.repository.ReservationJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationJpaRepository reservationJpaRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SpaceService spaceService;


    public List<Reservation> findAll() {
        return reservationJpaRepository.findAll();
    }

    public Reservation add(ReservationDTO reservationDTO) {
        Space space = spaceService.getById(reservationDTO.getSpaceIdId());
        User user = userService.getByEmail(reservationDTO.getEmail());
        Reservation reservation = new Reservation();

        reservation.setSpace(space);
        reservation.setUser(user);
        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setEndDate(reservationDTO.getEndDate());
        reservation.setStatus(StatusValores.PENDING);
        return reservationJpaRepository.save(reservation);
    }
}
