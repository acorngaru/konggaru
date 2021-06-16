package com.acorngaru.konggaru.controller;

import com.acorngaru.konggaru.model.OrderDetail;
import com.acorngaru.konggaru.model.Response;
import com.acorngaru.konggaru.security.MemberDetails;
import com.acorngaru.konggaru.util.CurrentUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String showCheckout(Model model,
                               @CurrentUser MemberDetails memberDetails) throws JsonProcessingException {
        model.addAttribute("member", objectMapper.writeValueAsString(memberDetails.member));

        return "client/checkout";
    }

    @PostMapping
    @ResponseBody
    public Response<?> insertOrderDetail(@RequestBody(required = false) List<OrderDetail> orderDetails,
                                         @CurrentUser MemberDetails memberDetails,
                                         HttpSession session) throws JsonProcessingException {
        log.info("insertOrderDetail() - {}", orderDetails);

        if (memberDetails.member == null) {
            log.info("insertOrderDetail() - Member is null");

            return Response.ERROR();
        }

        session.setAttribute("orderDetails", objectMapper.writeValueAsString(orderDetails));

        return Response.OK();
    }
}
