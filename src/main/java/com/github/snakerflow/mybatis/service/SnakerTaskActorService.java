package com.github.snakerflow.mybatis.service;

import com.github.snakerflow.mybatis.entity.TaskActorEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.snaker.engine.entity.TaskActor;

import java.util.List;

/**
 * @author zhaocheng
 */
public interface SnakerTaskActorService extends IService<TaskActorEntity>{


    /**
     * 根据任务id查询所有活动任务参与者集合
     * @param taskId /
     * @return /
     */
    List<TaskActor> getTaskActorsByTaskId(String taskId);
}
