package com.github.snakerflow.mybatis.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.snakerflow.mybatis.entity.TaskActorEntity;
import com.github.snakerflow.mybatis.mapper.TaskActorMapper;
import com.github.snakerflow.mybatis.service.SnakerTaskActorService;
import org.snaker.engine.entity.TaskActor;

import java.util.List;

/**
 * @author zhaocheng
 */
public class SnakerTaskActorServiceImpl extends ServiceImpl<TaskActorMapper, TaskActorEntity> implements SnakerTaskActorService {

    @Override
    public List<TaskActor> getTaskActorsByTaskId(String taskId) {
        return baseMapper.findListByTaskId(taskId);
    }
}
