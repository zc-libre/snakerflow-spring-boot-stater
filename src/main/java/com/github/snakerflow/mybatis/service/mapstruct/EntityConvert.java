package com.github.snakerflow.mybatis.service.mapstruct;

import com.github.snakerflow.mybatis.entity.*;
import com.github.snakerflow.util.MpPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.snaker.engine.access.Page;
import org.snaker.engine.entity.*;
import org.snaker.engine.entity.Process;

import java.util.List;

/**
 * @author zhaocheng
 */
@Mapper
public interface EntityConvert {

    EntityConvert INSTANCE = Mappers.getMapper(EntityConvert.class);
    /**
     * Process ===> ProcessEntity
     * @param process /
     * @return /
     */
    ProcessEntity toProcessEntity(Process process);

    /**
     * Order ===> OrderEntity
     * @param order /
     * @return /
     */
    OrderEntity toOrderEntity(Order order);

    /**
     * CCOrder ===> CcOrderEntity
     * @param ccOrder /
     * @return /
     */
    CcOrderEntity toCcOrderEntity(CCOrder ccOrder);

    /**
     * HistoryOrder ===> HistOrderEntity
     * @param historyOrder /
     * @return /
     */
    HistOrderEntity toHistOrderEntity(HistoryOrder historyOrder);

    /**
     * HistOrderEntity ===> HistoryOrder
     * @param histOrderEntity /
     * @return /
     */
    HistoryOrder toHistOrder(HistOrderEntity histOrderEntity);
    /**
     * Task ===> TaskEntity
     * @param task /
     * @return /
     */
    TaskEntity toTaskEntity(Task task);

    /**
     * TaskActor ===> TaskActorEntity
     * @param taskActor /
     * @return /
     */
    TaskActorEntity toTaskActorEntity(TaskActor taskActor);

    /**
     * HistoryTask ===> HistTaskEntity
     * @param historyTask /
     * @return /
     */
    HistTaskEntity toHistTaskEntity(HistoryTask historyTask);

    /**
     * HistoryTaskActor ===> HistTaskActorEntity
     * @param historyTaskActor /
     * @return /
     */
    HistTaskActorEntity toHistTaskActorEntity(HistoryTaskActor historyTaskActor);

    /**
     * Surrogate ===> SurrogateEntity
     * @param surrogate /
     * @return /
     */
    SurrogateEntity toSurrogateEntity(Surrogate surrogate);

    /**
     * Page ===> MpPage
     * @param page /
     * @return /
     */

    @Mapping(source = "pageNo", target = "current")
    @Mapping(source = "pageSize", target = "size")
    @Mapping(source = "totalCount", target = "total")
    @Mapping(source = "result", target = "records")
    MpPage<Surrogate> toSurrogateMpPage(Page<Surrogate> page);

    /**
     * MpPage ===> page
     * @param page /
     * @return /
     */
    @Mapping(source = "current", target = "pageNo")
    @Mapping(source = "current", target = "pageSize")
    @Mapping(source = "total", target = "totalCount")
    @Mapping(source = "records", target = "result")
    Page<Surrogate> toSurrogatePage(MpPage<Surrogate> page);


    /**
     * Page ===> MpPage
     * @param page /
     * @return /
     */
    @Mapping(source = "pageNo", target = "current")
    @Mapping(source = "pageSize", target = "size")
    @Mapping(source = "totalCount", target = "total")
    @Mapping(source = "result", target = "records")
    MpPage<ProcessEntity> toProcessMpPage(Page<Process> page);

    /**
     * List<ProcessEntity> ===> List<Process>
     * @param processEntities /
     * @return /
     */
    List<Process> toProcessList(List<ProcessEntity> processEntities);

    /**
     * Page ===> MpPage
     * @param page /
     * @return /
     */
    @Mapping(source = "pageNo", target = "current")
    @Mapping(source = "pageSize", target = "size")
    @Mapping(source = "totalCount", target = "total")
    @Mapping(source = "result", target = "records")
    MpPage<Order> toOrderMpPage(Page<Order> page);

    /**
     * MpPage ===> page
     * @param page /
     * @return /
     */
    @Mapping(source = "current", target = "pageNo")
    @Mapping(source = "current", target = "pageSize")
    @Mapping(source = "total", target = "totalCount")
    @Mapping(source = "records", target = "result")
    Page<Order> toOrderPage(MpPage<Order> page);

    /**
     * Page ===> MpPage
     * @param page /
     * @return /
     */
    @Mapping(source = "pageNo", target = "current")
    @Mapping(source = "pageSize", target = "size")
    @Mapping(source = "totalCount", target = "total")
    @Mapping(source = "result", target = "records")
    MpPage<Task> toTaskMpPage(Page<Task> page);

    /**
     * MpPage ===> page
     * @param page /
     * @return /
     */
    @Mapping(source = "current", target = "pageNo")
    @Mapping(source = "current", target = "pageSize")
    @Mapping(source = "total", target = "totalCount")
    @Mapping(source = "records", target = "result")
    Page<Task> toTaskPage(MpPage<Task> page);
    /**
     * Page ===> MpPage
     * @param page /
     * @return /
     */
    @Mapping(source = "pageNo", target = "current")
    @Mapping(source = "pageSize", target = "size")
    @Mapping(source = "totalCount", target = "total")
    @Mapping(source = "result", target = "records")
    MpPage<HistoryOrder> toHistoryOrderMpPage(Page<HistoryOrder> page);


    /**
     * MpPage ===> page
     * @param page /
     * @return /
     */
    @Mapping(source = "current", target = "pageNo")
    @Mapping(source = "current", target = "pageSize")
    @Mapping(source = "total", target = "totalCount")
    @Mapping(source = "records", target = "result")
    Page<HistoryOrder> toHistoryOrderPage(MpPage<HistoryOrder> page);
    /**
     * Page ===> MpPage
     * @param page /
     * @return /
     */
    @Mapping(source = "pageNo", target = "current")
    @Mapping(source = "pageSize", target = "size")
    @Mapping(source = "totalCount", target = "total")
    @Mapping(source = "result", target = "records")
    MpPage<HistoryTask> toHistoryTaskMpPage(Page<HistoryTask> page);



    /**
     * Page ===> MpPage
     * @param page /
     * @return /
     */
    @Mapping(source = "pageNo", target = "current")
    @Mapping(source = "pageSize", target = "size")
    @Mapping(source = "totalCount", target = "total")
    @Mapping(source = "result", target = "records")
    MpPage<WorkItem> toWorkItemTaskMpPage(Page<WorkItem> page);



    /**
     * Page ===> MpPage
     * @param page /
     * @return /
     */
    @Mapping(source = "pageNo", target = "current")
    @Mapping(source = "pageSize", target = "size")
    @Mapping(source = "totalCount", target = "total")
    @Mapping(source = "result", target = "records")
    MpPage<WorkItem> toWorkItemMpPage(Page<WorkItem> page);

    /**
     * MpPage ===> page
     * @param page /
     * @return /
     */
    @Mapping(source = "current", target = "pageNo")
    @Mapping(source = "current", target = "pageSize")
    @Mapping(source = "total", target = "totalCount")
    @Mapping(source = "records", target = "result")
    Page<WorkItem> toWorkItemPage(MpPage<WorkItem> page);


    HistoryTask toHistoryTask(HistTaskEntity histTaskEntity);
}
