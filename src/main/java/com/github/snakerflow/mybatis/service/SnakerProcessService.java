package com.github.snakerflow.mybatis.service;

import com.github.snakerflow.mybatis.entity.ProcessEntity;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * @author zhaocheng
 */
public interface SnakerProcessService extends IService<ProcessEntity>{


    Integer findLatestProcessVersionByName(String name);
}
