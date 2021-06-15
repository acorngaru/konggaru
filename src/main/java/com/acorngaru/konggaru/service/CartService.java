package com.acorngaru.konggaru.service;

import java.util.List;

import com.acorngaru.konggaru.model.Cart;
import com.acorngaru.konggaru.model.Product;

public interface CartService {
	
	List<Cart> findCartListByMemberId(int id) throws Exception;
	int updateCart(Cart cart) throws Exception;
	int deleteCart(int id) throws Exception;
	
}
