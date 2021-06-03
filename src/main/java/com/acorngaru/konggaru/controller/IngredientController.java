package com.acorngaru.konggaru.controller;

import com.acorngaru.konggaru.model.Ingredient;
import com.acorngaru.konggaru.model.Page;
import com.acorngaru.konggaru.service.IngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<Ingredient>> getIngredients(@RequestParam(required = false, defaultValue = "1") int pageNo,
                                                           @RequestParam(required = false, defaultValue = "10") int rows,
                                                           @RequestParam(required = false, defaultValue = "") String searchTerm) throws Exception {
        return new ResponseEntity<>(
                ingredientService.findIngredients(
                        pageNo,
                        rows,
                        searchTerm
                ),
                HttpStatus.OK
        );
    }
}
