package com.acorngaru.konggaru.controller;

import com.acorngaru.konggaru.model.Ingredient;
import com.acorngaru.konggaru.model.Ingredient_Order;
import com.acorngaru.konggaru.model.Page;
import com.acorngaru.konggaru.service.IngredientService;
import com.acorngaru.konggaru.service.Ingredient_OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
public class OrderController {

    @Autowired
    IngredientService service;
    @Autowired
    Ingredient_OrderService s;


    @RequestMapping(value = "/order")
    public String Order(ModelAndView modelAndView) throws  Exception{
        return "ingredient/order";
    }



    @RequestMapping(value = "/Ingredient/selectAll")
    public void selectAll(HttpServletResponse response) throws Exception{

        String name = "items";
        List<Ingredient> list = service.allIngredient();
        HashMap<String,List<Ingredient>> data = new HashMap<>();
        data.put(name,list);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(data);

        PrintWriter out = response.getWriter();
        out.println(json);
    }


    @RequestMapping(value = "/ingredient/orderAdd", method = RequestMethod.POST)
    public String OrderAdd(@RequestBody Ingredient_Order ingredient_order){
        int n =s.orderAddIngredient(ingredient_order);
        System.out.println("저장 완료>>>>>>"+n);

        return "/ingredient/order";
    }














}
