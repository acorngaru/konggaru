package com.acorngaru.konggaru.controller;

import com.acorngaru.konggaru.model.Ingredient;
import com.acorngaru.konggaru.model.Page;
import com.acorngaru.konggaru.service.IngredientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

    @PostMapping(value = "/list")
    public void showHome(
            @RequestParam("selectName") String name,
            @RequestParam("pageNo") int pageNo,
            HttpServletResponse res ) throws IOException {
        Page p = new Page();
        p.setPageCount(5);
        p.setCurrentPageNo(pageNo);
        HashMap<String,String> map = new HashMap<>();
        p.process(5,pageNo, service.countIngredientByName(name),service.allIngredient());

        map.put("first" , Integer.toString((p.getCurrentPageNo()-1)*p.getRows()+1));
        map.put("last", Integer.toString(p.getCurrentPageNo()*p.getRows()));
        map.put("name",name);
        p.setItems(service.searchIngredient(map));

        ObjectMapper om = new ObjectMapper();

        String json = om.writeValueAsString(p);

        res.setCharacterEncoding("UTF-8");
        PrintWriter out = res.getWriter();
        out.println(json);

    }
    @GetMapping("/deleteone")
    public String deleteIngredient(
            @RequestParam("name") String data,
            HttpServletRequest request
    ){

        int amount = service.ingredientDelete(Integer.parseInt(data));
        log.info("삭제 목록>>"+String.valueOf(amount));

        return "redirect:/" ;
    }
    @GetMapping("/deleteall")
    public String deleteIngredientAll(
            @RequestParam("name") String data,
            HttpServletRequest request
    ){

        String[] x = data.split(",");
        List<String> list = Arrays.asList(x);//List.생성
        System.out.println(list);



        int n = service.IngredientDelAll(list);
        log.info("실행된 레코드갯수: "+n);

        return "redirect:/" ;
    }

    @PostMapping("/updateone")
    public String updateIngredient(
            @RequestParam("update_id") String id,
            @RequestParam("update_name") String name,
            @RequestParam("update_partner") String update_partner,
            @RequestParam("update_price") String update_price,
            @RequestParam("update_quantity") String update_quantity,
            @RequestParam("update_unit") String update_unit
    ){

        Ingredient ingredient =
                new Ingredient(Integer.parseInt(id),name,
                        Integer.parseInt(update_quantity),
                        Integer.parseInt(update_price),
                        update_unit,update_partner);

        int n =service.updateIngredient(ingredient);

        log.info("업데이트 완료>>>>>>"+String.valueOf(n));

        return "redirect:/" ;

    }

    @PostMapping("/insertone")
    public String insert(
            @RequestParam("insert_name") String name,
            @RequestParam("insert_partner") String partner,
            @RequestParam("insert_quantity") String quantity,
            @RequestParam("insert_price") String price,
            @RequestParam("insert_unit") String unit
    ){
        int id = 0;
        Ingredient ingredient = new Ingredient(id,name,Integer.parseInt(quantity),
                Integer.parseInt(price),unit,partner);
        service.ingredientCreate(ingredient);
        log.info(String.valueOf(ingredient));
        return "redirect:/" ;
    }
}
