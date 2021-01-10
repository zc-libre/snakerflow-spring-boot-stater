package com.github.snakerflow.mybatis.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.github.snakerflow.mybatis.entity.CcOrderEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.snaker.engine.entity.CCOrder;

import java.util.List;

/**
 * @author zhaocheng
 */
public interface SnakerCcOrderService extends IService<CcOrderEntity>{

    /**
     * 根据流程实例id、参与者id获取抄送记录
     * @param orderId /
     * @param actorIds /
     * @return /
     */
    List<CCOrder> getCCOrder(String orderId, List<String> actorIds);
}
