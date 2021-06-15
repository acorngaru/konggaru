package com.acorngaru.konggaru.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acorngaru.konggaru.model.Cart;
import com.acorngaru.konggaru.model.Order;
import com.acorngaru.konggaru.service.OrderService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
	
	private final OrderService orderService;
	
    @GetMapping("/list")
    public String showOrderList() {
    	return "order/list";
    }
    
    @PostMapping("/show")
    @ResponseBody
    public List<Order> showOrderListById(@RequestBody int memberId) throws Exception {
    	return orderService.findOrdersByMemberId(memberId);
    }
    
}
