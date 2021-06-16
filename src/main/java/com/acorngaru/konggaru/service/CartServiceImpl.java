package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.mapper.CartMapper;
import com.acorngaru.konggaru.model.Cart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service("cartService")
public class CartServiceImpl implements CartService {
    private final CartMapper cartMapper;
    
	@Override
	public List<Cart> findCartListByMemberId(int id) throws Exception {
		return cartMapper.findCartListByMemberId(id);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void insert(Cart cart) throws Exception {
		Optional<Cart> existingCart = cartMapper.findCartByMemberIdAndProductId(cart.getMemberId(), cart.getProductId());
		if (existingCart.isPresent()) {
			existingCart.get().setProductQuantity(existingCart.get().getProductQuantity() + cart.getProductQuantity());

			cartMapper.updateCart(existingCart.get());
		} else {
			cartMapper.insert(cart);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateCart(Cart cart) throws Exception {
		return cartMapper.updateCart(cart);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int deleteCart(int id) {
		return cartMapper.deleteCart(id);
	}
}
