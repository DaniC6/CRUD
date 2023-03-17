package com.CRUD.controller;

import com.CRUD.Car;
import com.CRUD.repository.CarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @PostMapping
    public Car postCar(@RequestBody Car car) {
        Car carSaved = carRepository.saveAndFlush(car);
        return carSaved;
    }

    @GetMapping("/carlist")
    public List<Car> getUsers(){
        List<Car> cars = carRepository.findAll();
        return cars;
    }


    @GetMapping("/{id}")
    public Car getCar(@PathVariable("id") Long id){
        Optional<Car> optionalCar = carRepository.findById(id);
        return optionalCar.orElse(new Car());
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable("id") Long id,@RequestBody String type){
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            car.setType(type);
            carRepository.save(car);
            return car;
        } else {
            return new Car();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable("id") Long id){
        carRepository.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll(){
        carRepository.deleteAll();
    }

}

