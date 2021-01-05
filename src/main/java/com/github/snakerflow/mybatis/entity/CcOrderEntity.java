package com.github.snakerflow.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 抄送实例表
 * @author zhaocheng
 */
@Data
@TableName(value = "wf_cc_order")
public class CcOrderEntity implements Serializable {
    /**
     * 流程实例ID
     */
    @TableField(value = "order_Id")
    private String orderId;

    /**
     * 参与者ID
     */
    @TableField(value = "actor_Id")
    private String actorId;

    /**
     * 发起人
     */
    @TableField(value = "creator")
    private String creator;

    /**
     * 抄送时间
     */
    @TableField(value = "create_Time")
    private String createTime;

    /**
     * 完成时间
     */
    @TableField(value = "finish_Time")
    private String finishTime;

    /**
     * 状态
     */
    @TableField(value = "`status`")
    private Integer status;

}
