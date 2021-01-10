package com.github.snakerflow.mybatis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.snakerflow.mybatis.entity.TaskEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.snakerflow.util.MpPage;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Task;
import org.snaker.engine.entity.WorkItem;

import java.util.List;

/**
 * @author zhaocheng
 */
public interface SnakerTaskService extends IService<TaskEntity>{

    /**
     * 通过id查找
     * @param id /
     * @return /
     */
    Task getTaskById(String id);

    /**
     * 根据父任务id查询所有子任务
     * @param parentTaskId /
     * @return /
     */
    List<Task> getNextActiveTasks(String parentTaskId);

    /**
     * 根据流程实例id、任务名称获取
     * @param orderId /
     * @param taskName /
     * @param parentTaskId /
     * @return /
     */
    List<Task> getNextActiveTasks(String orderId, String taskName, String parentTaskId);

    /**
     * 分页查询活动任务列表
     * @param page /
     * @param filter /
     * @return /
     */
    List<Task> getActiveTasks(Page<Task> page, QueryFilter filter);


    List<WorkItem> getWorkItems(Page<WorkItem> page, QueryFilter filter);
}
