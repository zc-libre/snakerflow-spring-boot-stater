package com.github.snakerflow.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
    * 委托代理表
    */
@Data
@TableName(value = "wf_surrogate")
public class SurrogateEntity implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 流程名称
     */
    @TableField(value = "process_Name")
    private String processName;

    /**
     * 授权人
     */
    @TableField(value = "`operator`")
    private String operator;

    /**
     * 代理人
     */
    @TableField(value = "surrogate")
    private String surrogate;

    /**
     * 操作时间
     */
    @TableField(value = "odate")
    private String odate;

    /**
     * 开始时间
     */
    @TableField(value = "sdate")
    private String sdate;

    /**
     * 结束时间
     */
    @TableField(value = "edate")
    private String edate;

    /**
     * 状态
     */
    @TableField(value = "`state`")
    private Integer state;

}
