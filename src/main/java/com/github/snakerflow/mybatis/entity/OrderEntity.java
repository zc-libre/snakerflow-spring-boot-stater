package com.github.snakerflow.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 流程实例表
 * @author zhao.cheng
 */
@Data
@TableName(value = "wf_order")
public class OrderEntity implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 父流程ID
     */
    @TableField(value = "parent_Id")
    private String parentId;

    /**
     * 流程定义ID
     */
    @TableField(value = "process_Id")
    private String processId;

    /**
     * 发起人
     */
    @TableField(value = "creator")
    private String creator;

    /**
     * 发起时间
     */
    @TableField(value = "create_Time")
    private String createTime;

    /**
     * 期望完成时间
     */
    @TableField(value = "expire_Time")
    private String expireTime;

    /**
     * 上次更新时间
     */
    @TableField(value = "last_Update_Time")
    private String lastUpdateTime;

    /**
     * 上次更新人
     */
    @TableField(value = "last_Updator")
    private String lastUpdator;

    /**
     * 优先级
     */
    @TableField(value = "priority")
    private Integer priority;

    /**
     * 父流程依赖的节点名称
     */
    @TableField(value = "parent_Node_Name")
    private String parentNodeName;

    /**
     * 流程实例编号
     */
    @TableField(value = "order_No")
    private String orderNo;

    /**
     * 附属变量json存储
     */
    @TableField(value = "`variable`")
    private String variable;

    /**
     * 版本
     */
    @TableField(value = "version")
    private Integer version;

}
