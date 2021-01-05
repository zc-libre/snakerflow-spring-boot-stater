package com.github.snakerflow.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 任务参与者表
 * @author zhaocheng
 */
@Data
@TableName(value = "wf_task_actor")
public class TaskActorEntity implements Serializable {
    /**
     * 任务ID
     */
    @TableField(value = "task_Id")
    private String taskId;

    /**
     * 参与者ID
     */
    @TableField(value = "actor_Id")
    private String actorId;

}
