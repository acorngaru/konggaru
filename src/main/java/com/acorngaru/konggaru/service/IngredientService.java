package com.acorngaru.konggaru.service;


import com.acorngaru.konggaru.mapper.IngredientMapper;
import com.acorngaru.konggaru.model.Ingredient;
import com.acorngaru.konggaru.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
@Service
@Transactional
public class IngredientService  {
    @Autowired
    IngredientMapper mapper;
    public Page<Ingredient> searchIngredient(String name,int pageNo, int n, List<Ingredient> list) {
        Page p = new Page();
        p.setPageCount(5);
        HashMap<String,String> map = new HashMap<>();
        p.process(
                5,
                pageNo,
                n,
                list
        );
        map.put("first" , Integer.toString((p.getCurrentPageNo()-1)*p.getRows()+1));
        map.put("last", Integer.toString(p.getCurrentPageNo()*p.getRows()));
        map.put("name",name);
        p.setItems(mapper.searchIngredient(map));
        return p;

    }
    public List<Ingredient> allIngredient() {
        return mapper.allIngredient();
    }
    public int countIngredientByName(String name) {
        int amount = mapper.countIngredientByName(name);
        return amount;
    }
    public int ingredientDel(int n) {
        int amount = mapper.ingredientDel(n);
        return amount;
    }
    public int updateIngredient(Ingredient ingredient) {
        int n = mapper.updateIngredient(ingredient);
        return n;
    }
    public int create(Ingredient ingredient) {
        int n = mapper.create(ingredient);
        return n;
    }
    public int ingredientDelAll(List<String> arrayList) {
        int n = mapper.ingredientDelAll(arrayList);
        return n;
    }

}