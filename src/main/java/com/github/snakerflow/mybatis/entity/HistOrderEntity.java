package com.github.snakerflow.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 历史流程实例表
 * @author zhaocheng
 */
@Data
@TableName(value = "wf_hist_order")
public class HistOrderEntity implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 流程定义ID
     */
    @TableField(value = "process_Id")
    private String processId;

    /**
     * 状态
     */
    @TableField(value = "order_State")
    private Integer orderState;

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
     * 完成时间
     */
    @TableField(value = "end_Time")
    private String endTime;

    /**
     * 期望完成时间
     */
    @TableField(value = "expire_Time")
    private String expireTime;

    /**
     * 优先级
     */
    @TableField(value = "priority")
    private Integer priority;

    /**
     * 父流程ID
     */
    @TableField(value = "parent_Id")
    private String parentId;

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

}
