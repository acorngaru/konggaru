package com.acorngaru.konggaru.controller;

import com.acorngaru.konggaru.model.Cart;
import com.acorngaru.konggaru.model.Response;
import com.acorngaru.konggaru.security.MemberDetails;
import com.acorngaru.konggaru.service.CartService;
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
@RequestMapping("/cart")
public class CartController {
	private final CartService cartService;
	private final ObjectMapper objectMapper;

    @GetMapping("/list")
    public String showCartList(Model model,
                               @CurrentUser MemberDetails memberDetails) throws Exception {
        model.addAttribute(
                "carts", objectMapper.writeValueAsString(cartService.findCartListByMemberId(memberDetails.member.getMemberId())));

    	return "cart/list";
    }
    
    @PostMapping("/show")
    @ResponseBody
    public List<Cart> showCartListById(@RequestBody int id) throws Exception {
    	return cartService.findCartListByMemberId(id);
    }

    @PostMapping("/insert")
    @ResponseBody
    public Response<?> insertCart(@RequestBody Cart cart,
                                  @CurrentUser MemberDetails memberDetails) throws Exception {
        log.info("insertCart() - cart: {}", cart);

        cart.setMemberId(memberDetails.member.getMemberId());
        cartService.insert(cart);

        return Response.OK();
    }
    
    @PostMapping("/update")
    @ResponseBody
    public int updateCart(@RequestBody Cart cart) throws Exception {
        log.info("updateCart() - {}", cart);

    	return cartService.updateCart(cart);
    }
    
    @DeleteMapping
    @ResponseBody
    public int deleteCart(@RequestBody Cart cart) throws Exception {
    	log.info("deleteCart() - {}", cart);

    	return cartService.deleteCart(cart.getId());
    }
}
