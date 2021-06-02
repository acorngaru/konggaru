package com.acorngaru.konggaru.controller;

import com.acorngaru.konggaru.model.Ingredient;
import com.acorngaru.konggaru.model.Page;
import com.acorngaru.konggaru.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    IngredientService service;

    @GetMapping
    public String showHome() {
        return "ingredient/ingredient";
    }

    @ResponseBody
    @PostMapping(value = "/list")
    public Page showHome(
            @RequestParam("selectName") String name,
            @RequestParam("pageNo") int pageNo) {
        int count = service.countIngredientByName(name);
        List<Ingredient> list = service.allIngredient();
        Page p = service.searchIngredient(name, pageNo,count,list);
        return p;


    }

    @PostMapping("/deleteone")
    public String deleteIngredient(
            @RequestParam("name") String data
    ){

        int amount = service.ingredientDel(Integer.parseInt(data));
        log.info("삭제 목록>>"+String.valueOf(amount));

        return "redirect:ingredient/ingredient" ;
    }

    @PostMapping("/deleteall")
    public void deleteIngredientAll(
            @RequestParam("name") List<String> list
    ){
        service.ingredientDelAll(list);
    }

    @PostMapping("/updateone")
    public void updateIngredient(
            @RequestBody Ingredient ingredient
    ){
        int n =service.updateIngredient(ingredient);
        log.info("업데이트 완료>>>>>>"+String.valueOf(n));
    }

    @PostMapping("/insertone")
    public void insert(
            @RequestBody Ingredient ingredient
    ){
        service.create(ingredient);
        log.info(String.valueOf(ingredient));
    }
}
