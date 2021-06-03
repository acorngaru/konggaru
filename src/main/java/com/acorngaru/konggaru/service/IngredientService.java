package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.model.Ingredient;
import com.acorngaru.konggaru.model.Page;

public interface IngredientService {

    Page<Ingredient> findIngredients(int pageNo, int rows, String searchTerm) throws Exception;
}
