package com.github.snakerflow.mybatis.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.snakerflow.mybatis.mapper.SurrogateMapper;
import com.github.snakerflow.mybatis.entity.SurrogateEntity;
import com.github.snakerflow.mybatis.service.SnakerSurrogateService;
import com.github.snakerflow.util.MpPage;
import lombok.extern.slf4j.Slf4j;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Surrogate;

import java.util.List;

/**
 * @author zhaocheng
 */
@Slf4j
public class SnakerSurrogateServiceImpl extends ServiceImpl<SurrogateMapper, SurrogateEntity> implements SnakerSurrogateService {

    @Override
    public Surrogate getSurrogateById(String id) {
       return baseMapper.findById(id);
    }

    @Override
    public List<Surrogate> getSurrogates(QueryFilter filter) {
        return baseMapper.findList(filter);
    }

    @Override
    public MpPage<Surrogate> getSurrogates(MpPage<Surrogate> mpPage, QueryFilter filter) {
        return baseMapper.findList(mpPage, filter);
    }

}
