package com.github.snakerflow.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.snakerflow.mybatis.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Order;

import java.util.List;

/**
 * @author zhaocheng
 */

public interface OrderMapper extends BaseMapper<OrderEntity> {
    /**
     * 通过id获取
     * @param orderId /
     * @return /
     */
    @Select("select o.id,o.process_Id,o.creator,o.create_Time,o.parent_Id,o.parent_Node_Name,o.expire_Time,o.last_Update_Time,o.last_Updator,o.priority,o.order_No,o.variable, o.version from wf_order o where id = #{orderId}")
    Order findById(String orderId);

    /**
     * 获取活动的流程实例
     * @param page /
     * @param filter /
     * @return /
     */
    List<Order> findActiveOrders(Page<Order> page, @Param("filter") QueryFilter filter);
}
