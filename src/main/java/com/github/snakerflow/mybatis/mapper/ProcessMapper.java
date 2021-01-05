package com.github.snakerflow.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.snakerflow.mybatis.entity.ProcessEntity;
import org.apache.ibatis.annotations.Select;

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
}
