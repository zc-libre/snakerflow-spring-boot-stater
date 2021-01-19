package com.github.snakerflow.mybatis.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.snakerflow.mybatis.entity.OrderEntity;
import com.github.snakerflow.mybatis.mapper.OrderMapper;
import com.github.snakerflow.mybatis.service.SnakerOrderService;
import com.github.snakerflow.util.MpPage;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Order;

import java.util.List;

/**
 * @author zhaocheng
 */
public class SnakerOrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements SnakerOrderService {

    @Override
    public Order getOrderById(String orderId) {
        return baseMapper.findById(orderId);
    }

    @Override
    public MpPage<Order> getActiveOrders(Page<Order> page, QueryFilter filter) {
        return baseMapper.findActiveOrders(page, filter);
    }

    @Override
    public List<Order> getActiveOrders(QueryFilter filter) {
        return  baseMapper.findActiveOrders(filter);
    }
}
