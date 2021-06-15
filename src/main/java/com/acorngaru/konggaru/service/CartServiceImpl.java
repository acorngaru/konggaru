package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.mapper.CartMapper;
import com.acorngaru.konggaru.model.Cart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service("cartService")
public class CartServiceImpl implements CartService {
    private final CartMapper cartMapper;
    
	@Override
	public List<Cart> findCartListByMemberId(int id) throws Exception {
		return cartMapper.findCartListByMemberId(id);
	}
	
	@Override
	public int updateCart(Cart cart) throws Exception {
		return cartMapper.updateCart(cart);
	}

	@Override
	public int deleteCart(int id) throws Exception {
		return cartMapper.deleteCart(id);
	}

}
