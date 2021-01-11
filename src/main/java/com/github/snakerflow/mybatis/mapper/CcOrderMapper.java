package com.github.snakerflow.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.snakerflow.mybatis.entity.CcOrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.snaker.engine.entity.CCOrder;

import java.util.List;


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
