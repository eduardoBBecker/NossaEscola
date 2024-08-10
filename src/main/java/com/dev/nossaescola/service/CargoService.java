package com.dev.nossaescola.service;

import com.dev.nossaescola.data.CargoEntity;
import com.dev.nossaescola.data.CargoRepository;
import java.util.List;
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

    public List<CargoEntity> listarTodosCargos() {
        List<CargoEntity> cargos = cargoRepository.findAll();
        return cargos;
    }

    public CargoEntity findById(Integer id) {
        return cargoRepository.findById(id).orElseThrow(() -> new RuntimeException("Cargo não encontrado!"));
    }

}
