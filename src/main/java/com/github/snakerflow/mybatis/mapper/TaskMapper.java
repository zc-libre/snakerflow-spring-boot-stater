package com.github.snakerflow.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.snakerflow.mybatis.entity.TaskEntity;
import com.github.snakerflow.util.MpPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Task;
import org.snaker.engine.entity.WorkItem;

import java.util.List;

/**
 * @author zhaocheng
 */

public interface TaskMapper extends BaseMapper<TaskEntity> {

    /**
     * 通过ID查找
     * @param id /
     * @return /
     */
    @Select("select id,order_Id,task_Name,display_Name,task_Type,perform_Type,operator,create_Time,finish_Time,expire_Time,action_Url,parent_Task_Id,variable, version from wf_task where id = #{id}")
    Task findById(String id);

    /**
     * 根据父任务id查询所有子任务
     * @param parentTaskId /
     * @return /
     */
    @Select("select id,order_Id,task_Name,display_Name,task_Type,perform_Type,operator,create_Time,finish_Time,expire_Time,action_Url,parent_Task_Id,variable, version from wf_task where parent_Task_Id = #{parentTaskId}")
    List<Task> findListByParentTaskId(String parentTaskId);

    /**
     * 根据流程实例id、任务名称获取
     * @param orderId /
     * @param taskName /
     * @param parentTaskId /
     * @return /
     */
    List<Task> findTaskList(@Param("orderId") String orderId, @Param("taskName") String taskName, @Param("parentTaskId") String parentTaskId);

    /**
     * 条件查询
     * @param filter /
     * @return /
     */
    List<Task> findActiveTasks(@Param("filter") QueryFilter filter);
    /**
     * findActiveTasks
     * @param page /
     * @param filter /
     * @return /
     */
    MpPage<Task> findActiveTasks(Page<Task> page, @Param("filter") QueryFilter filter);




    List<WorkItem> findWorkItems(@Param("filter")QueryFilter filter);
    /**
     *
     * @param page
     * @param filter
     * @return
     */
    MpPage<WorkItem> findWorkItems(Page<WorkItem> page, @Param("filter")QueryFilter filter);
}
