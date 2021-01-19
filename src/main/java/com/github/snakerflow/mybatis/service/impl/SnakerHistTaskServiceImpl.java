package com.github.snakerflow.mybatis.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.snakerflow.mybatis.mapper.HistTaskMapper;
import com.github.snakerflow.mybatis.entity.HistTaskEntity;
import com.github.snakerflow.mybatis.service.SnakerHistTaskService;
import com.github.snakerflow.util.MpPage;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.WorkItem;

import java.util.List;

/**
 * @author zhaocheng
 */
public class SnakerHistTaskServiceImpl extends ServiceImpl<HistTaskMapper, HistTaskEntity> implements SnakerHistTaskService {

    @Override
    public List<HistoryTask> getHistoryTasks(QueryFilter filter) {
        return baseMapper.findListPage(filter);
    }

    @Override
    public MpPage<HistoryTask> getHistoryTasks(Page<HistoryTask> page, QueryFilter filter) {
        return baseMapper.findListPage(page, filter);
    }

    @Override
    public List<WorkItem> getHistoryWorkItems(QueryFilter filter) {
        return baseMapper.findHistoryWorkItems(filter);
    }

    @Override
    public MpPage<WorkItem> getHistoryWorkItems(Page<WorkItem> page, QueryFilter filter) {
        return baseMapper.findHistoryWorkItems(page, filter);
    }
}
