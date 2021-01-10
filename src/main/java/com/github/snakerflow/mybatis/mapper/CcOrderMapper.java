package com.github.snakerflow.mybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.snakerflow.mybatis.entity.CcOrderEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.snaker.engine.entity.CCOrder;

import java.util.List;

import static com.baomidou.mybatisplus.core.toolkit.Constants.WRAPPER;

/**
 * @author zhaocheng
 */

public interface CcOrderMapper extends BaseMapper<CcOrderEntity> {

    /**
     * 根据流程实例id、参与者id获取集合
     * @param orderId /
     * @param actorIds /
     * @return /
     */
    List<CCOrder> findListByOrderIdAndActorIds(@Param("orderId") String orderId, @Param("actorIds") List<String> actorIds);
}
