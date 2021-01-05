package com.github.snakerflow.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
    * 任务表
    */
@Data
@TableName(value = "wf_task")
public class TaskEntity implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 流程实例ID
     */
    @TableField(value = "order_Id")
    private String orderId;

    /**
     * 任务名称
     */
    @TableField(value = "task_Name")
    private String taskName;

    /**
     * 任务显示名称
     */
    @TableField(value = "display_Name")
    private String displayName;

    /**
     * 任务类型
     */
    @TableField(value = "task_Type")
    private Integer taskType;

    /**
     * 参与类型
     */
    @TableField(value = "perform_Type")
    private Integer performType;

    /**
     * 任务处理人
     */
    @TableField(value = "`operator`")
    private String operator;

    /**
     * 任务创建时间
     */
    @TableField(value = "create_Time")
    private String createTime;

    /**
     * 任务完成时间
     */
    @TableField(value = "finish_Time")
    private String finishTime;

    /**
     * 任务期望完成时间
     */
    @TableField(value = "expire_Time")
    private String expireTime;

    /**
     * 任务处理的url
     */
    @TableField(value = "action_Url")
    private String actionUrl;

    /**
     * 父任务ID
     */
    @TableField(value = "parent_Task_Id")
    private String parentTaskId;

    /**
     * 附属变量json存储
     */
    @TableField(value = "`variable`")
    private String variable;

    /**
     * 版本
     */
    @TableField(value = "version")
    private Integer version = 0;

}
