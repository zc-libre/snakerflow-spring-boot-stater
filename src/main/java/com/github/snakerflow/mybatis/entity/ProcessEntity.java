package com.github.snakerflow.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 流程定义表
 * @author zhaocheng
 */
@Data
@TableName(value = "wf_process")
public class ProcessEntity implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 流程名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 流程显示名称
     */
    @TableField(value = "display_Name")
    private String displayName;

    /**
     * 流程类型
     */
    @TableField(value = "`type`")
    private String type;

    /**
     * 实例url
     */
    @TableField(value = "instance_Url")
    private String instanceUrl;

    /**
     * 流程是否可用
     */
    @TableField(value = "`state`")
    private Integer state;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 创建时间
     */
    @TableField(value = "create_Time")
    private String createTime;

    /**
     * 创建人
     */
    @TableField(value = "creator")
    private String creator;

    /**
     * 流程模型定义
     */
    @TableField(value = "content")
    private byte[] bytes;

}
