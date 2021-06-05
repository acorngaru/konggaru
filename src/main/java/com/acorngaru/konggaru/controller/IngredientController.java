package com.acorngaru.konggaru.controller;

import com.acorngaru.konggaru.model.Ingredient;
import com.acorngaru.konggaru.model.Page;
import com.acorngaru.konggaru.service.IngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping
    public String showIngredientList() {
        return "ingredient/ingredient";
    }

    @ResponseBody
    @PostMapping("/list")
    public ResponseEntity<Page<Ingredient>> getIngredients(
            @RequestParam(required = false, defaultValue = "") String searchTerm,
            @RequestParam(required = false, defaultValue = "1") int pageNo,
            @RequestParam(required = false, defaultValue = "5") int rows
    ) throws Exception {
        return new ResponseEntity<>(
                ingredientService.findIngredients(pageNo, rows, searchTerm),
                HttpStatus.OK
        );
    }

    @PostMapping("/deleteone")
    public String deleteIngredient(
            @RequestParam("name") int id
    ) {
        log.info("deleteIngredient() - id: {}", id);

        ingredientService.deleteIngredientById(id);

        return "redirect:/ingredient";
    }

    @PostMapping("/deleteall")
    public String deleteIngredients(
            @RequestParam("name") List<Integer> ids
    ) {
        log.info("deleteIngredients() - ids: {}", ids);

        ingredientService.deleteIngredientsById(ids);

        return "redirect:/ingredient";
    }

    @PostMapping("/updateone")
    public ResponseEntity<?> updateIngredient(
            @RequestBody Ingredient ingredient
    ) {
        log.info("updateIngredient() - ingredient: {}", ingredient);

        ingredientService.update(ingredient);

        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }

    @PostMapping("/insertone")
    public ResponseEntity<?> insertIngredient(
            @RequestBody Ingredient ingredient
    ) {
        log.info("insertIngredient() - ingredient: {}", ingredient);

        ingredientService.create(ingredient);

        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }
}
