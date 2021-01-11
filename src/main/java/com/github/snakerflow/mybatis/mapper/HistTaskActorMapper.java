package com.github.snakerflow.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.snakerflow.mybatis.entity.HistTaskActorEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.snaker.engine.entity.HistoryTaskActor;

import java.util.List;

/**
 * @author zhaocheng
 */

public interface HistTaskActorMapper extends BaseMapper<HistTaskActorEntity> {
    /**
     * 通过任务id获取集合
     * @param taskId /
     * @return /
     */
    @Select("SELECT task_Id, actor_Id FROM wf_hist_task_actor WHERE task_Id = #{taskId}")
    List<HistoryTaskActor> findListByTaskId(String taskId);
}
