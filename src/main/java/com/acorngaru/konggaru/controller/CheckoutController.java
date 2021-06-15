package com.acorngaru.konggaru.controller;

import com.acorngaru.konggaru.model.OrderDetail;
import com.acorngaru.konggaru.model.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/checkout")
public class CheckoutController {
    private final ObjectMapper objectMapper;

    @GetMapping
    public String showCheckout() {
        return "client/checkout";
    }

    @PostMapping
    @ResponseBody
    public Response<?> insertOrderDetail(@RequestBody List<OrderDetail> orderDetails,
                                         HttpSession session) throws JsonProcessingException {
        log.info("insertOrderDetail() - {}", orderDetails);

        session.setAttribute("orderDetails", objectMapper.writeValueAsString(orderDetails));

        return Response.OK();
    }
}
