package com.github.snakerflow.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
    * 历史任务参与者表
 * @author zhaocheng
 */
@Data
@TableName(value = "wf_hist_task_actor")
public class HistTaskActorEntity implements Serializable {
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
