package com.github.snakerflow.mybatis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.snakerflow.mybatis.entity.HistTaskEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.snakerflow.util.MpPage;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.WorkItem;

import java.util.List;

/**
 * @author zhaocheng
 */
public interface SnakerHistTaskService extends IService<HistTaskEntity>{


    List<HistoryTask> getHistoryTasks(QueryFilter filter);

    MpPage<HistoryTask> getHistoryTasks(Page<HistoryTask> page, QueryFilter filter);

    List<WorkItem> getHistoryWorkItems(QueryFilter filter);

    MpPage<WorkItem> getHistoryWorkItems(Page<WorkItem> page, QueryFilter filter);
}
