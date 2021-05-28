package com.acorngaru.konggaru.mapper;

import com.acorngaru.konggaru.model.Ingredient;

import java.util.HashMap;
import java.util.List;

public interface IngredientMapper {
    public List<Ingredient> searchIngredient(HashMap<String, String> map);
    public List<Ingredient> allIngredient();
    public int countIngredientByName(String name);
    public int ingredientDel(int n);
    public int updateIngredient(Ingredient ingredient);
    public int create(Ingredient ingredient);
    public int ingredientDelAll(List<String> arrayList);
}
