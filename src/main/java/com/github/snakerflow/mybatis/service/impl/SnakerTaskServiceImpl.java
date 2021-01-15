package com.github.snakerflow.mybatis.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.snakerflow.mybatis.mapper.TaskMapper;
import com.github.snakerflow.mybatis.entity.TaskEntity;
import com.github.snakerflow.mybatis.service.SnakerTaskService;
import com.github.snakerflow.util.MpPage;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Task;
import org.snaker.engine.entity.WorkItem;

import java.util.List;

/**
 * @author zhaocheng
 */
public class SnakerTaskServiceImpl extends ServiceImpl<TaskMapper, TaskEntity> implements SnakerTaskService {

    @Override
    public Task getTaskById(String id) {
        return baseMapper.findById(id);
    }

    @Override
    public List<Task> getNextActiveTasks(String parentTaskId) {
        return baseMapper.findListByParentTaskId(parentTaskId);
    }

    @Override
    public List<Task> getNextActiveTasks(String orderId, String taskName, String parentTaskId) {
        return baseMapper.findTaskList(orderId, taskName, parentTaskId);
    }

    @Override
    public MpPage<Task> getActiveTasks(Page<Task> page, QueryFilter filter) {
        return baseMapper.findActiveTasks(page, filter);
    }

    @Override
    public List<Task> getActiveTasks(QueryFilter filter) {
        return baseMapper.findActiveTasks(filter);
    }

    @Override
    public List<WorkItem> getWorkItems(QueryFilter filter) {
        return baseMapper.findWorkItems(filter);
    }

    @Override
    public MpPage<WorkItem> getWorkItems(Page<WorkItem> page, QueryFilter filter) {
        return baseMapper.findWorkItems(page, filter);
    }
}
