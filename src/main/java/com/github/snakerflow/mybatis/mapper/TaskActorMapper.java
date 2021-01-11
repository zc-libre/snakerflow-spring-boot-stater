package com.github.snakerflow.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.snakerflow.mybatis.entity.TaskActorEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.snaker.engine.entity.TaskActor;

import java.util.List;

/**
 * @author zhaocheng
 */

public interface TaskActorMapper extends BaseMapper<TaskActorEntity> {
    /**
     * 通过任务id获取集合
     * @param taskId /
     * @return /
     */
    @Select("SELECT task_Id, actor_Id FROM wf_task_actor where task_Id = #{taskId}")
    List<TaskActor> findListByTaskId(String taskId);
}
