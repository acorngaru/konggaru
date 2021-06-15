package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.mapper.OrderMapper;
import com.acorngaru.konggaru.model.Order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
    private final OrderMapper orderMapper;

	@Override
	public List<Order> findOrdersByMemberId(int memberId) throws Exception {
		return orderMapper.findOrdersByMemberId(memberId);
	}

}
