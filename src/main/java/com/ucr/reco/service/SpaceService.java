package com.ucr.reco.service;


import com.ucr.reco.model.Space;
import com.ucr.reco.model.User;
import com.ucr.reco.repository.SpaceJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpaceService {

    @Autowired
    private SpaceJpaRepository repository;


    public List<Space> findAll() {
        return repository.findAll();
    }

    public Space getById(Integer id) {
        return repository.getById(id);
    }

    public Space add(Space space) {
        Space spaceExits = repository.getByName(space.getName());
        if (spaceExits != null) {
            return null;
        } else {
            if (space.getName() == null || space.getLocation() == null || space.getType() == null || space.getPrice() == null) {
                return null;
            }
        }
        return repository.save(space);
    }

    public Space update(Space space) {
        Optional<Space> spaceExits = repository.findById(space.getId());

        if (spaceExits.isPresent()) {
            Space spaceN = spaceExits.get();

            if (space.getName() != null) {
                spaceN.setName(space.getName());
            }
            if (space.getLocation() != null) {
                spaceN.setLocation(space.getLocation());
            }
            if (space.getType() != null) {
                spaceN.setType(space.getType());
            }
            if (space.getPrice() != null) {
                spaceN.setPrice(space.getPrice());
            }

        } else {
            return null;
        }

        return repository.save(space);
    }

    public Space delete(Integer id) {
        Optional<Space> spaceExits = repository.findById(id);
        if (spaceExits.isPresent()) {
            repository.deleteById(id);
            return spaceExits.get();
        }
        return null;
    }


    public Space changePrice(Integer id, Double price) {
        Optional<Space> spaceExits = repository.findById(id);
        if (spaceExits.isPresent()) {
            Space space = spaceExits.get();
            space.setPrice(price);

            return repository.save(space);
        }
        return null;
    }

}
