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
     * @param <T> /
     * @return /
     */
    @Mapping(source = "pageNo", target = "current")
    @Mapping(source = "pageSize", target = "size")
    @Mapping(source = "totalCount", target = "total")
    @Mapping(source = "result", target = "records")
    MpPage<Surrogate> toSurrogateMpPage(Page<Surrogate> page);

}
