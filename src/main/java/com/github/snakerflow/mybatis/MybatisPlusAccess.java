package com.github.snakerflow.mybatis;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.snakerflow.mybatis.entity.*;
import com.github.snakerflow.mybatis.service.*;
import com.github.snakerflow.mybatis.service.mapstruct.EntityConvert;
import lombok.Getter;
import lombok.Setter;
import org.snaker.engine.DBAccess;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.*;
import org.snaker.engine.entity.Process;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhaocheng
 */
@Setter
@Getter
public class MybatisPlusAccess implements DBAccess {

    private SnakerProcessService snakerProcessService;
    private SnakerOrderService snakerOrderService;
    private SnakerHistOrderService snakerHistOrderService;
    private SnakerCcOrderService snakerCcOrderService;
    private SnakerTaskService snakerTaskService;
    private SnakerTaskActorService snakerTaskActorService;
    private SnakerHistTaskService snakerHistTaskService;
    private SnakerSurrogateService snakerSurrogateService;
    private EntityConvert entityConvert;

    @Override
    public void initialize(Object o) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveTask(Task task) {
        TaskEntity taskEntity = entityConvert.toTaskEntity(task);
        snakerTaskService.saveOrUpdate(taskEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(Order order) {
        OrderEntity orderEntity = entityConvert.toOrderEntity(order);
        snakerOrderService.saveOrUpdate(orderEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCCOrder(CCOrder ccOrder) {
        CcOrderEntity ccOrderEntity = entityConvert.toCcOrderEntity(ccOrder);
        snakerCcOrderService.saveOrUpdate(ccOrderEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveProcess(Process process) {
        ProcessEntity processEntity = entityConvert.toProcessEntity(process);
        snakerProcessService.saveOrUpdate(processEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveTaskActor(TaskActor taskActor) {
        TaskActorEntity taskActorEntity = entityConvert.toTaskActorEntity(taskActor);
        snakerTaskActorService.saveOrUpdate(taskActorEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTask(Task task) {
        TaskEntity taskEntity = entityConvert.toTaskEntity(task);
        snakerTaskService.saveOrUpdate(taskEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOrder(Order order) {
        OrderEntity orderEntity = entityConvert.toOrderEntity(order);
        snakerOrderService.saveOrUpdate(orderEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCCOrder(CCOrder ccOrder) {
        CcOrderEntity ccOrderEntity = entityConvert.toCcOrderEntity(ccOrder);
        snakerCcOrderService.saveOrUpdate(ccOrderEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProcess(Process process) {
        ProcessEntity processEntity = entityConvert.toProcessEntity(process);
        snakerProcessService.saveOrUpdate(processEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProcess(Process process) {
        snakerProcessService.removeById(process.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProcessType(String id, String type) {
        snakerProcessService.update(Wrappers.<ProcessEntity>lambdaUpdate()
                .set(ProcessEntity::getType, type)
                .eq(ProcessEntity::getId, id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTask(Task task) {
        snakerTaskActorService.remove(Wrappers.<TaskActorEntity>lambdaUpdate()
                .eq(TaskActorEntity::getTaskId, task.getId()));
        snakerTaskService.removeById(task.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOrder(Order order) {
       snakerOrderService.removeById(order.getId());
    }

    @Override
    public void deleteCCOrder(CCOrder ccOrder) {
         snakerCcOrderService.remove(Wrappers.<CcOrderEntity>lambdaUpdate()
                 .eq(CcOrderEntity::getOrderId, ccOrder.getOrderId())
                 .eq(CcOrderEntity::getActorId, ccOrder.getActorId()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeTaskActor(String taskId, String... actors) {
        for (String actorId : actors) {
            snakerTaskActorService.remove(Wrappers.<TaskActorEntity>lambdaUpdate()
                    .eq(TaskActorEntity::getTaskId, taskId)
                    .eq(TaskActorEntity::getActorId, actorId));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveHistory(HistoryOrder historyOrder) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateHistory(HistoryOrder historyOrder) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveHistory(HistoryTask historyTask) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteHistoryOrder(HistoryOrder historyOrder) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteHistoryTask(HistoryTask historyTask) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOrderVariable(Order order) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSurrogate(Surrogate surrogate) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSurrogate(Surrogate surrogate) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSurrogate(Surrogate surrogate) {

    }

    @Override
    public Surrogate getSurrogate(String s) {
        return null;
    }

    @Override
    public List<Surrogate> getSurrogate(Page<Surrogate> page, QueryFilter queryFilter) {
        return null;
    }

    @Override
    public Task getTask(String s) {
        return null;
    }

    @Override
    public HistoryTask getHistTask(String s) {
        return null;
    }

    @Override
    public List<Task> getNextActiveTasks(String s) {
        return null;
    }

    @Override
    public List<Task> getNextActiveTasks(String s, String s1, String s2) {
        return null;
    }

    @Override
    public List<TaskActor> getTaskActorsByTaskId(String s) {
        return null;
    }

    @Override
    public List<HistoryTaskActor> getHistTaskActorsByTaskId(String s) {
        return null;
    }

    @Override
    public Order getOrder(String s) {
        return null;
    }

    @Override
    public List<CCOrder> getCCOrder(String s, String... strings) {
        return null;
    }

    @Override
    public HistoryOrder getHistOrder(String s) {
        return null;
    }

    @Override
    public Process getProcess(String s) {
        return null;
    }

    @Override
    public Integer getLatestProcessVersion(String name) {
        return snakerProcessService.findLatestProcessVersionByName(name);

    }

    @Override
    public List<Process> getProcesss(Page<Process> page, QueryFilter queryFilter) {
        return null;
    }

    @Override
    public List<Order> getActiveOrders(Page<Order> page, QueryFilter queryFilter) {
        return null;
    }

    @Override
    public List<Task> getActiveTasks(Page<Task> page, QueryFilter queryFilter) {
        return null;
    }

    @Override
    public List<HistoryOrder> getHistoryOrders(Page<HistoryOrder> page, QueryFilter queryFilter) {
        return null;
    }

    @Override
    public List<HistoryTask> getHistoryTasks(Page<HistoryTask> page, QueryFilter queryFilter) {
        return null;
    }

    @Override
    public List<WorkItem> getWorkItems(Page<WorkItem> page, QueryFilter queryFilter) {
        return null;
    }

    @Override
    public List<HistoryOrder> getCCWorks(Page<HistoryOrder> page, QueryFilter queryFilter) {
        return null;
    }

    @Override
    public List<WorkItem> getHistoryWorkItems(Page<WorkItem> page, QueryFilter queryFilter) {
        return null;
    }

    @Override
    public <T> T queryObject(Class<T> aClass, String s, Object... objects) {
        return null;
    }

    @Override
    public <T> List<T> queryList(Class<T> aClass, String s, Object... objects) {
        return null;
    }

    @Override
    public <T> List<T> queryList(Page<T> page, QueryFilter queryFilter, Class<T> aClass, String s, Object... objects) {
        return null;
    }

    @Override
    public void runScript() {

    }
}
