package com.github.snakerflow.mybatis.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.snakerflow.mybatis.mapper.HistTaskActorMapper;
import com.github.snakerflow.mybatis.entity.HistTaskActorEntity;
import com.github.snakerflow.mybatis.service.SnakerHistTaskActorService;
import org.snaker.engine.entity.HistoryTaskActor;

import java.util.List;

/**
 * @author zhaocheng
 */
public class SnakerHistTaskActorServiceImpl extends ServiceImpl<HistTaskActorMapper, HistTaskActorEntity> implements SnakerHistTaskActorService {

    @Override
    public List<HistoryTaskActor> getHistTaskActorsByTaskId(String taskId) {
        return baseMapper.findListByTaskId(taskId);
    }
}
