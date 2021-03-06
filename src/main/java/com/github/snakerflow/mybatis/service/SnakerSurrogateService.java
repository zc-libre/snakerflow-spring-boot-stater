package com.github.snakerflow.mybatis.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.snakerflow.mybatis.entity.SurrogateEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.snakerflow.util.MpPage;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Surrogate;

import java.util.List;

/**
 * @author zhaocheng
 */
public interface SnakerSurrogateService extends IService<SurrogateEntity>{

    /**
     * 通过id查询
     * @param id /
     * @return /
     */
    Surrogate getSurrogateById(String id);



    List<Surrogate> getSurrogates(QueryFilter filter);


    MpPage<Surrogate> getSurrogates(MpPage<Surrogate> mpPage, QueryFilter filter);
}
