package com.yakovlev.testTask.controllers;

import com.yakovlev.testTask.entities.Car;
import com.yakovlev.testTask.entities.User;
import com.yakovlev.testTask.exceptions.CarNotFoundException;
import com.yakovlev.testTask.exceptions.UserNotFoundException;
import com.yakovlev.testTask.services.MainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {
    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/car/getAll")
    public List<Car> getAllCars() {
        return mainService.getAllCars();
    }

    @GetMapping("/user/addCar")
    public ResponseEntity addCar(@RequestParam("userId") String userId, @RequestParam("carId") String carId) {
        try {
            mainService.addCar(userId, carId);
        } catch (CarNotFoundException | UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/find/{sequence}")
    public ResponseEntity findWithAnySequence(@PathVariable("sequence") String sequence) {
        try {
            return new ResponseEntity(mainService.findWithAnySequence(sequence), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/getAll")
    public List<User> getAllUser() {
        return mainService.getAllUsers();
    }

}
