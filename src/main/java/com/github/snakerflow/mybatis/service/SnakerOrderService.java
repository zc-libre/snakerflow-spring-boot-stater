package com.github.snakerflow.mybatis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.snakerflow.mybatis.entity.OrderEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.snakerflow.util.MpPage;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Order;

import java.util.List;

/**
 * @author zhaocheng
 */
public interface SnakerOrderService extends IService<OrderEntity>{


    /**
     * 根据流程实例id查询实例对象
     * @param orderId /
     * @return /
     */
    Order getOrderById(String orderId);

    /**
     * 分页查询流程实例
     * @param mpPage /
     * @param filter /
     * @return /
     */
    List<Order> getActiveOrders(Page<Order> mpPage, QueryFilter filter);
}
