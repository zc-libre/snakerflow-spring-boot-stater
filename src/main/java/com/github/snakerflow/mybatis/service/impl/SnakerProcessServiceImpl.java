package com.github.snakerflow.mybatis.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.snakerflow.mybatis.entity.ProcessEntity;
import com.github.snakerflow.mybatis.mapper.ProcessMapper;
import com.github.snakerflow.mybatis.service.SnakerProcessService;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Process;

import java.util.List;

/**
 * @author zhaocheng
 */
public class SnakerProcessServiceImpl extends ServiceImpl<ProcessMapper, ProcessEntity> implements SnakerProcessService {

    @Override
    public Integer findLatestProcessVersionByName(String name) {
        return baseMapper.selectLatestProcessVersionByName(name);
    }

    @Override
    public Process getProcessById(String id) {
        return baseMapper.findById(id);
    }

    @Override
    public List<Process> findList(Page<ProcessEntity> page, QueryFilter filter) {
        return baseMapper.findList(page, filter);
    }
}
