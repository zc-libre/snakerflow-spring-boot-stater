package com.github.snakerflow.mybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.snakerflow.mybatis.entity.SurrogateEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.snaker.engine.entity.Surrogate;

import java.util.List;

import static com.baomidou.mybatisplus.core.toolkit.Constants.WRAPPER;


/**
 * @author zhaocheng
 */
public interface SurrogateMapper extends BaseMapper<SurrogateEntity> {



    /**
     * 通過id查找
     * @param id /
     * @return /
     */
    @Select("select id, process_Name, operator, surrogate, odate, sdate, edate, state from wf_surrogate where id = #{id}")
    Surrogate findById(String id);

    /**
     * 分頁条件查询
     * @param page /
     * @param wrapper /
     * @return /
     */
    @Select("select id, process_Name, operator, surrogate, odate, sdate, edate, state from wf_surrogate ${ew.customSqlSegment}")
    List<Surrogate> findOne(Page page, @Param(WRAPPER)Wrapper<SurrogateEntity> wrapper);
}
