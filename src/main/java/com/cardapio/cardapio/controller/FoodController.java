package com.cardapio.cardapio.controller;

import com.cardapio.cardapio.food.Food;
import com.cardapio.cardapio.food.FoodRepository;
import com.cardapio.cardapio.food.FoodRequestDTO;
import com.cardapio.cardapio.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("food")
public class FoodController {

    @Autowired
    private FoodRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FoodResponseDTO> getAllFood() {
        List<FoodResponseDTO> foodsList = repository.findAll().stream().map(FoodResponseDTO::new).toList();

        return foodsList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void postFood(@RequestBody FoodRequestDTO data){
        Food foodData = new Food(data);
        repository.save(foodData);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<FoodResponseDTO> putFood(@PathVariable long id, @RequestBody FoodRequestDTO data) {
        Optional<Food> optionalFood = repository.findById(id);

        if (optionalFood.isPresent()) {
            Food foodItem = optionalFood.get();

            foodItem.setTitle(data.title());
            foodItem.setImage(data.image());
            foodItem.setPrice(data.price());

            repository.save(foodItem);

            return ResponseEntity.ok(new FoodResponseDTO(foodItem));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable long id){
        try{
            repository.deleteById(id);

            return ResponseEntity.noContent().build();
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PatchMapping("/{id}")
    public ResponseEntity<FoodResponseDTO> patchFood(@PathVariable long id, @RequestBody FoodRequestDTO data){
        Optional<Food> optionalFood = repository.findById(id);

        if (optionalFood.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Food foodItem = optionalFood.get();

        if (data.title() != null) {
            foodItem.setTitle(data.title());
        }
        if (data.image() != null) {
             foodItem.setImage(data.image());
            }
        if (data.price() != null) {
            foodItem.setPrice(data.price());
        }

        repository.save(foodItem);

        return ResponseEntity.ok(new FoodResponseDTO(foodItem));
    }

}