package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.mapper.IngredientPageMapper;
import com.acorngaru.konggaru.model.Ingredient_Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class Ingredient_OrderService {

    @Autowired
    IngredientPageMapper mapper;

    public int orderAddIngredient(int ingredient_id, String ingredient_name,
                                  int ingredient_quantity, String created_at,
                                  String addmission_at) {
        System.out.println("service 실행됨");

        int n = mapper.orderAddIngredient(ingredient_id,ingredient_name,ingredient_quantity,created_at,addmission_at);
        System.out.println("mapper 실행됨"+n);
        return n;
    }
}
