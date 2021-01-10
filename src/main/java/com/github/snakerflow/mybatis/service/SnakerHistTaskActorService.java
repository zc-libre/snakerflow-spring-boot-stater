package com.github.snakerflow.mybatis.service;

import com.github.snakerflow.mybatis.entity.HistTaskActorEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.snaker.engine.entity.HistoryTaskActor;

import java.util.List;

/**
 * @author zhaocheng
 */
public interface SnakerHistTaskActorService extends IService<HistTaskActorEntity>{


    /**
     * 根据任务id查询所有历史任务参与者集合
     * @param taskId /
     * @return /
     */
    List<HistoryTaskActor> getHistTaskActorsByTaskId(String taskId);
}
