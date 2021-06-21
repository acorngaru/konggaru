package com.acorngaru.konggaru.service;

import java.util.List;

import com.acorngaru.konggaru.model.Order;

public interface OrderService {
	
	List<Order> findOrdersByMemberId(int memberId) throws Exception;
	void insert(Order order);
}
