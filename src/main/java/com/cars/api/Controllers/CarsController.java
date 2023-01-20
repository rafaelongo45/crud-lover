package com.cars.api.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cars.api.Model.Car;
import com.cars.api.dto.CarDTO;
import com.cars.api.repository.CarRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/car")
public class CarsController {

  @Autowired
  private CarRepository carRepository;
  
  @PostMapping
  public void create(@RequestBody @Valid CarDTO req){
    carRepository.save(new Car(req));
  }

  @GetMapping
  public List<Car> listAll(){
    return carRepository.findAll();
  }

  @PutMapping("/{id}")
  public void update(@PathVariable Long id, @RequestBody @Valid CarDTO req){
    carRepository.findById(id).map(car -> {
      car.setAnoModelo(req.anoModelo());
      car.setDataFabricacao(req.dataFabricacao());
      car.setFabricante(req.fabricante());
      car.setModelo(req.modelo());
      car.setValor(req.valor());
      return carRepository.save(car);
    });

  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id){
    carRepository.deleteById(id);
  }
}
