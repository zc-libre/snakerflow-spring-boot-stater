package com.github.snakerflow.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.snakerflow.mybatis.entity.ProcessEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Process;

import java.util.List;

/**
 * @author zhaocheng
 */

public interface ProcessMapper extends BaseMapper<ProcessEntity> {

    /**
     * 获取最大版本号
     * @param name /
     * @return /
     */
    @Select("SELECT max(version) FROM wf_process WHERE name = #{name}")
    Integer selectLatestProcessVersionByName(String name);

    /**
     * 通过id查找
     * @param id /
     * @return /
     */

    Process findById(String id);

    /**
     *
     * @param page
     * @param filter
     * @return
     */
    List<Process> findList(Page<ProcessEntity> page, @Param("filter") QueryFilter filter);
}
