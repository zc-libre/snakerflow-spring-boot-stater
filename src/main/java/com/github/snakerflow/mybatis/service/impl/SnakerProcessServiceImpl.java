package com.github.snakerflow.mybatis.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.snakerflow.mybatis.entity.ProcessEntity;
import com.github.snakerflow.mybatis.mapper.ProcessMapper;
import com.github.snakerflow.mybatis.service.SnakerProcessService;
/**
 * @author zhaocheng
 */
public class SnakerProcessServiceImpl extends ServiceImpl<ProcessMapper, ProcessEntity> implements SnakerProcessService {

    @Override
    public Integer findLatestProcessVersionByName(String name) {
        return baseMapper.selectLatestProcessVersionByName(name);
    }
}
