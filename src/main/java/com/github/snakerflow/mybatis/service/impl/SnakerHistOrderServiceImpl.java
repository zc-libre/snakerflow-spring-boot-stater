package com.github.snakerflow.mybatis.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.snakerflow.mybatis.entity.HistOrderEntity;
import com.github.snakerflow.mybatis.mapper.HistOrderMapper;
import com.github.snakerflow.mybatis.service.SnakerHistOrderService;
import com.github.snakerflow.util.MpPage;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryOrder;

import java.util.List;

/**
 * @author zhaocheng
 */
public class SnakerHistOrderServiceImpl extends ServiceImpl<HistOrderMapper, HistOrderEntity> implements SnakerHistOrderService {


    @Override
    public List<HistoryOrder> getHistoryOrders(QueryFilter filter) {
        return baseMapper.findList(filter);
    }

    @Override
    public MpPage<HistoryOrder> getHistoryOrders(Page<HistoryOrder> page, QueryFilter filter) {
        return baseMapper.findList(page, filter);
    }

    @Override
    public List<HistoryOrder> getCCWorks(QueryFilter filter) {
        return baseMapper.getCCWorks(filter);
    }

    @Override
    public MpPage<HistoryOrder> getCCWorks(Page<HistoryOrder> page, QueryFilter filter) {
        return baseMapper.getCCWorks(page, filter);
    }
}
