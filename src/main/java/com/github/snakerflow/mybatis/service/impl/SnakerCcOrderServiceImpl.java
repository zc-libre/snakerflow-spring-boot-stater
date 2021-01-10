package com.github.snakerflow.mybatis.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.snakerflow.mybatis.entity.CcOrderEntity;
import com.github.snakerflow.mybatis.mapper.CcOrderMapper;
import com.github.snakerflow.mybatis.service.SnakerCcOrderService;
import org.snaker.engine.entity.CCOrder;

import java.util.List;


/**
 * @author zhaocheng
 */
public class SnakerCcOrderServiceImpl extends ServiceImpl<CcOrderMapper, CcOrderEntity> implements SnakerCcOrderService {

    @Override
    public List<CCOrder> getCCOrder(String orderId, List<String> actorIds) {
        return baseMapper.findListByOrderIdAndActorIds(orderId, actorIds);
    }
}
