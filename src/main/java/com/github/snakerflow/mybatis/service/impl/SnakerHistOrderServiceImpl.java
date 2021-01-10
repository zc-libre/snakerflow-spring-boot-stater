package com.github.snakerflow.mybatis.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.snakerflow.mybatis.entity.HistOrderEntity;
import com.github.snakerflow.mybatis.mapper.HistOrderMapper;
import com.github.snakerflow.mybatis.service.SnakerHistOrderService;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryOrder;

import java.util.List;

/**
 * @author zhaocheng
 */
public class SnakerHistOrderServiceImpl extends ServiceImpl<HistOrderMapper, HistOrderEntity> implements SnakerHistOrderService {


    @Override
    public List<HistoryOrder> getHistoryOrders(Page<HistoryOrder> page, QueryFilter filter) {
        return baseMapper.findListPage(page, filter);
    }

    @Override
    public List<HistoryOrder> getCCWorks(Page<HistoryOrder> page, QueryFilter filter) {
        return baseMapper.getCCWorks(page, filter);
    }
}
