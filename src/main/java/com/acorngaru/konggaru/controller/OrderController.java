package com.acorngaru.konggaru.controller;

import com.acorngaru.konggaru.model.Order;
import com.acorngaru.konggaru.model.Response;
import com.acorngaru.konggaru.security.MemberDetails;
import com.acorngaru.konggaru.service.OrderService;
import com.acorngaru.konggaru.util.CurrentUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
	private final OrderService orderService;
	private final ObjectMapper objectMapper;
	
    @GetMapping("/list")
    public String showOrderList(Model model,
                                @CurrentUser MemberDetails memberDetails) throws Exception {
        log.info("orders: {}", orderService.findOrdersByMemberId(memberDetails.member.getMemberId()));
        model.addAttribute(
                "orders", objectMapper.writeValueAsString(orderService.findOrdersByMemberId(memberDetails.member.getMemberId()))
        );

    	return "order/list";
    }
    
    @PostMapping("/show")
    @ResponseBody
    public List<Order> showOrderListById(@RequestBody int memberId) throws Exception {
    	return orderService.findOrdersByMemberId(memberId);
    }

    @PostMapping
    @ResponseBody
    public Response<?> insertOrder(@RequestBody Order order,
                                   @CurrentUser MemberDetails memberDetails) {
        log.info("insertOrder() - order: {}", order);

        order.setMemberId(memberDetails.member.getMemberId());
        orderService.insert(order);

        return Response.OK();
    }
}