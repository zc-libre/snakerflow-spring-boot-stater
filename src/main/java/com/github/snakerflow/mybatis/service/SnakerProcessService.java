package com.github.snakerflow.mybatis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.snakerflow.mybatis.entity.ProcessEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.snakerflow.util.MpPage;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Process;

import java.util.List;

/**
 * @author zhaocheng
 */
public interface SnakerProcessService extends IService<ProcessEntity>{

    /**
     * 通过名字获取
     * @param name /
     * @return /
     */
    Integer findLatestProcessVersionByName(String name);

    /**
     * 通过id查找
     * @param id /
     * @return /
     */
    Process getProcessById(String id);


    List<Process> findList(Page<ProcessEntity> page, QueryFilter filter);
}
