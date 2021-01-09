package com.github.snakerflow.mybatis.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.snakerflow.mybatis.entity.SurrogateEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.snakerflow.util.MpPage;
import org.snaker.engine.entity.Surrogate;

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

    /**
     * 分页条件查询
     * @param mpPage /
     * @param wrapper /
     * @return /
     */
    IPage findOne(MpPage mpPage, Wrapper<SurrogateEntity> wrapper);
}
