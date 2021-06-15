package com.acorngaru.konggaru.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acorngaru.konggaru.model.Cart;
import com.acorngaru.konggaru.model.Product;
import com.acorngaru.konggaru.service.CartService;
import com.acorngaru.konggaru.util.StringToObject;
import com.fasterxml.jackson.core.JsonParser;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
	
	private final CartService cartService;

    @GetMapping("/list")
    public String showCartList() {
    	return "cart/list";
    }
    
    @PostMapping("/show")
    @ResponseBody
    public List<Cart> showCartListById(@RequestBody int id) throws Exception {
    	return cartService.findCartListByMemberId(id);
    }
    
    @PostMapping("/update")
    @ResponseBody
    public int updateCart(@RequestBody Map<String, String> map) throws Exception {
    	Cart cart = new Cart();
    	int id = Integer.parseInt(map.get("cartId"));
    	int productQuantity = Integer.parseInt(map.get("cartQuantity"));
    	cart.setId(id);
    	cart.setProductQuantity(productQuantity);
    	return cartService.updateCart(cart);
    }
    
    @DeleteMapping
    @ResponseBody
    public int deleteCart(@RequestBody int id) throws Exception {
    	System.err.println(id);
    	return cartService.deleteCart(id);
    }
    
    ////////////////
    
    
    
}
