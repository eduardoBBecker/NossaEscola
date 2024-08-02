package com.dev.nossaescola.service;

import com.dev.nossaescola.data.CargoEntity;
import com.dev.nossaescola.data.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoService {

    @Autowired
    CargoRepository cargoRepository;

    public CargoEntity criarCargo(CargoEntity cargo) {

        cargo.setId(null);
        cargoRepository.save(cargo);
        return cargo;
    }

}
