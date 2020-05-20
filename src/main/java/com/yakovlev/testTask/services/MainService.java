package com.yakovlev.testTask.services;

import com.yakovlev.testTask.entities.Car;
import com.yakovlev.testTask.entities.User;
import com.yakovlev.testTask.exceptions.CarNotFoundException;
import com.yakovlev.testTask.exceptions.UserNotFoundException;
import com.yakovlev.testTask.repositories.CarRepository;
import com.yakovlev.testTask.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public MainService(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public void addCar(String userId, String carId) throws CarNotFoundException, UserNotFoundException {
        Car car = carRepository.findById(Long.parseLong(carId)).orElseThrow(() -> new CarNotFoundException(String.format("Car with id: %s not found", carId)));
        User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(() -> new UserNotFoundException(String.format("User with id: %s not found", userId)));
        car.setUser(user);
        carRepository.save(car);
    }

    public List<User> findWithAnySequence(String sequence) throws UserNotFoundException {
        return userRepository.findBySequence("%" + sequence + "%").orElseThrow(() -> new UserNotFoundException(String.format("Users wis sequence \"%s\" not found", sequence)));
    }
}
