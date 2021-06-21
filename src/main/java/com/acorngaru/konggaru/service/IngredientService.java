package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.model.Ingredient;
import com.acorngaru.konggaru.model.Page;

import java.util.List;

public interface IngredientService {

    List<Ingredient> findAllIngredients();
    Page<Ingredient> findIngredients(int pageNo, int rows, String searchTerm) throws Exception;
    void update(Ingredient ingredient);
    void create(Ingredient ingredient);
    void deleteIngredientById(int id);
    void deleteIngredientsById(List<Integer> ids);
    List<Ingredient> allIngredient();

}
