package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.mapper.OrderDetailMapper;
import com.acorngaru.konggaru.mapper.OrderMapper;
import com.acorngaru.konggaru.model.Order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("orderService")
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final OrderMapper orderMapper;
	private final OrderDetailMapper orderDetailMapper;

	@Override
	public List<Order> findOrdersByMemberId(int memberId) {
		return orderMapper.findOrdersByMemberId(memberId);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void insert(Order order) {
		orderMapper.insert(order);
		order.getOrderDetails().forEach(orderDetail -> {
			orderDetail.setOrderId(order.getId());
			orderDetailMapper.insert(orderDetail);
		});
	}
}
