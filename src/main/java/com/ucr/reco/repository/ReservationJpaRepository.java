package com.ucr.reco.repository;

import com.ucr.reco.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationJpaRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findAll();

    Reservation save(Reservation reservacion);

    Reservation getById(Integer id);

    boolean existsById(Integer id);

    //Reservation getByEmail(String email);

    //boolean existsByEmail(String email);

    //Reservation getBySpaceId(Integer id);

    // boolean existBySpaceId(Integer id);
}
