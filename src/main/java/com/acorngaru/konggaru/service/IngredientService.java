package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.dao.IngredientDAO;
import com.acorngaru.konggaru.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class IngredientService {
    @Autowired
    IngredientDAO dao ;



    public List<Ingredient> searchIngredient(HashMap<String, String> map) {
        List<Ingredient> searchlist = dao.searchIngredient(map);
        return searchlist;
    }
    public List<Ingredient> allIngredient() {
        return dao.allIngredient();
    }
    public int countIngredientByName(String name) {
        int amount = dao.countIngredientByName(name);
        return amount;
    }

    public int ingredientDelete(int name) {
        int p = dao.ingredientDel(name);
        return p;
    }

    public int updateIngredient(Ingredient ingredient) {

        int n = dao.updateIngredient(ingredient);
        return n;
    }
    public void ingredientCreate(Ingredient ingredient) {
        int n= dao.create(ingredient);
    }

    public int IngredientDelAll(List<String> list) {
        int n = dao.ingredientDelAll(list);
        return n;
    }

}