package com.acorngaru.konggaru.controller;

import com.acorngaru.konggaru.model.Ingredient;
import com.acorngaru.konggaru.model.IngredinetOrder;
import com.acorngaru.konggaru.service.IngredientOrderService;
import com.acorngaru.konggaru.service.IngredientOrderServiceImpl;
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
    IngredientOrderService s;


    @RequestMapping(value = "/ingredient/order")
    public String Order(ModelAndView modelAndView) throws  Exception{
        return "ingredient/order";
    }



    @RequestMapping(value = "/ingredient/selectAll")
    @ResponseBody
    public HashMap<String,List<Ingredient>> selectAll(){

        String name = "items";
        List<Ingredient> list = service.findAllIngredients();
        HashMap<String,List<Ingredient>> data = new HashMap<>();
        data.put(name,list);
        return data;
    }


    @RequestMapping(value = "/ingredient/orderAdd", method = RequestMethod.POST)
    public String OrderAdd(@RequestBody IngredinetOrder ingredient_order){
        int n =s.orderAddIngredient(ingredient_order);
        log.info("저장 완료>>>>>>"+n);

        return "/ingredient/order";
    }

    @RequestMapping(value = "/ingredient/selectAllOrder")
    @ResponseBody
    public HashMap<String,List<IngredinetOrder>> selectAllOrder(){

        String orderItem = "items";
        List<IngredinetOrder> list = s.selectAllOrder();
        HashMap<String,List<IngredinetOrder>> data = new HashMap<>();
        data.put(orderItem,list);
        return data;
    }

    @RequestMapping(value = "/ingredient/orderCreatedAt", method = RequestMethod.POST)
    public String OrderCreatedAt(@RequestParam("ingredient_id") int ingredient_id,
                                 @RequestParam("created_at") String created_at){

        Map<String,Object> map = new HashMap<>();
        map.put("ingredient_id",ingredient_id);
        map.put("created_at",created_at);
        int n =s.orderCreatedAt(map);


        return "/ingredient/order";
    }

    @RequestMapping(value = "/ingredient/addMissionAt", method = RequestMethod.POST)
    public String AddMissionAt(@RequestParam("addmission_at") String addmission_at){
        int n =s.addMissionAt(addmission_at);
        if (n!=0){
            List<IngredinetOrder>list=s.selectAllOrder();
            Map<String,Object> map = new HashMap<>();
            for (int i = 0; i < list.size(); i++) {
                map.put("name",list.get(i).getIngredient_name());
                map.put("quantity",list.get(i).getIngredient_quantity());
                int m = s.updateIngredientAdd(map);
            }//for
            int a = s.ingredientOrderDel();
        }//if
        return "/ingredient/order";
    }

}
