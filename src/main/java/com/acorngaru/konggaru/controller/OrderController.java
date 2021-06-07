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



    @RequestMapping(value = "/Ingredient/orderAdd", method = RequestMethod.POST)
    public void OrderAdd(@RequestBody Ingredient_Order ingredient_order){

        System.out.println("OrederController페이지 ===============================");

        System.out.println(ingredient_order);

        int ingredient_id = ingredient_order.getIngredient_id();
        String ingredient_name = ingredient_order.getIngredient_name();
        int ingredient_quantity = ingredient_order.getIngredient_quantity();
        String created_at = ingredient_order.getCreated_at();
        String addmission_at = ingredient_order.getAddmission_at();


        Ingredient_OrderService service = new Ingredient_OrderService();
        /*Ingredient_Order ingre_order = new Ingredient_Order(ingredient_id,ingredient_name,ingredient_quantity,created_at,addmission_at);*/
        int n =service.orderAddIngredient(ingredient_id,ingredient_name,ingredient_quantity,created_at,addmission_at);
        System.out.println("저장 완료>>>>>>"+n);



    }














}
