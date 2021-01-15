package com.github.snakerflow.mybatis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.snakerflow.mybatis.entity.HistOrderEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.snakerflow.util.MpPage;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryOrder;

import java.util.List;

/**
 * @author zhaocheng
 */
public interface SnakerHistOrderService extends IService<HistOrderEntity>{



    List<HistoryOrder> getHistoryOrders( QueryFilter filter);

    MpPage<HistoryOrder> getHistoryOrders(Page<HistoryOrder> page, QueryFilter filter);

    List<HistoryOrder> getCCWorks(QueryFilter filter);

    MpPage<HistoryOrder> getCCWorks(Page<HistoryOrder> page, QueryFilter filter);
}
