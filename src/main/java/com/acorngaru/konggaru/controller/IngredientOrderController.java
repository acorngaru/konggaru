package com.acorngaru.konggaru.controller;

import com.acorngaru.konggaru.model.Ingredient;
import com.acorngaru.konggaru.model.IngredientOrder;
import com.acorngaru.konggaru.service.IngredientOrderService;
import com.acorngaru.konggaru.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping()
public class IngredientOrderController {
    @Autowired
    IngredientService service;
    @Autowired
    IngredientOrderService ios;


    @RequestMapping(value = "/ingredient/order")
    public String Order(){
        return "ingredient/order";
    }



    @RequestMapping(value = "/ingredient/select-all")
    @ResponseBody
    public HashMap<String,List<Ingredient>> selectAll(){

        String name = "items";
        List<Ingredient> list = service.findAllIngredients();
        HashMap<String,List<Ingredient>> data = new HashMap<>();
        data.put(name,list);
        return data;
    }


    @RequestMapping(value = "/ingredient/order-add", method = RequestMethod.POST)
    public String OrderAdd(@RequestBody IngredientOrder ingredientOrder){
        int n =ios.orderAddIngredient(ingredientOrder);
        log.info("저장 완료>>>>>>"+n);

        return "/ingredient/order";
    }

    @RequestMapping(value = "/ingredient/select-all-order",method =  RequestMethod.POST)
    @ResponseBody
    public HashMap<String,List<IngredientOrder>> selectAllOrder(){

        String orderItem = "items";
        List<IngredientOrder> list = ios.selectAllOrder();
        HashMap<String,List<IngredientOrder>> data = new HashMap<>();
        data.put(orderItem,list);
        return data;
    }

    @RequestMapping(value = "/ingredient/order-created-at", method = RequestMethod.POST)
    public String OrderCreatedAt(@RequestParam("ingredient_id") int ingredientId,
                                 @RequestParam("created_at") String createdAt){

        Map<String,Object> map = new HashMap<>();
        map.put("ingredientId",ingredientId);
        map.put("createdAt",createdAt);
        System.out.println(map);
        int n =ios.orderCreatedAt(map);


        return "/ingredient/order";
    }

    @RequestMapping(value = "/ingredient/addmission-at", method = RequestMethod.POST)
    public String AddMissionAt(@RequestParam("addmission_at") String addMissionAt){
        int n =ios.addMissionAt(addMissionAt);
        if (n!=0){
            List<IngredientOrder>list=ios.selectAllOrder();
            Map<String,Object> map = new HashMap<>();
            for (int i = 0; i < list.size(); i++) {
                map.put("name",list.get(i).getIngredientName());
                map.put("quantity",list.get(i).getIngredientQuantity());
                int m = ios.updateIngredientAdd(map);
            }//for
            int a = ios.ingredientOrderDel();
        }//if
        return "/ingredient/order";
    }

}
