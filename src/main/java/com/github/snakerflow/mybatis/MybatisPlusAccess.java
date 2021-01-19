package com.github.snakerflow.mybatis;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.snakerflow.mybatis.entity.*;
import com.github.snakerflow.mybatis.service.*;
import com.github.snakerflow.mybatis.service.mapstruct.EntityConvert;
import com.github.snakerflow.prop.SnakerFlowProperties;
import com.github.snakerflow.util.MpPage;
import com.github.snakerflow.util.PageUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.snaker.engine.DBAccess;
import org.snaker.engine.SnakerException;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.access.ScriptRunner;
import org.snaker.engine.access.jdbc.JdbcHelper;
import org.snaker.engine.entity.*;
import org.snaker.engine.entity.Process;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author zhaocheng
 */
@Slf4j
@Setter
@Getter
@SuppressWarnings("unchecked")
public class MybatisPlusAccess implements DBAccess {

    private final SnakerFlowProperties properties;
    private final DataSource dataSource;
    private SnakerProcessService snakerProcessService;
    private SnakerOrderService snakerOrderService;
    private SnakerHistOrderService snakerHistOrderService;
    private SnakerCcOrderService snakerCcOrderService;
    private SnakerTaskService snakerTaskService;
    private SnakerTaskActorService snakerTaskActorService;
    private SnakerHistTaskService snakerHistTaskService;
    private SnakerHistTaskActorService histTaskActorService;
    private SnakerSurrogateService snakerSurrogateService;
    private EntityConvert entityConvert;

    public MybatisPlusAccess(SnakerFlowProperties properties, DataSource dataSource) {
        this.properties = properties;
        this.dataSource = dataSource;
    }

    @Override
    public void initialize(Object o) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveTask(Task task) {
        TaskEntity taskEntity = entityConvert.toTaskEntity(task);
        TaskEntity dbTask = snakerTaskService.getById(task.getId());
        if (Objects.nonNull(dbTask)) {
            snakerTaskService.updateById(taskEntity);
            return;
        }
        snakerTaskService.save(taskEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(Order order) {
        OrderEntity orderEntity = entityConvert.toOrderEntity(order);
        Order dbOrder = snakerOrderService.getOrderById(order.getId());
        if (Objects.nonNull(dbOrder)) {
            snakerOrderService.updateById(orderEntity);
            return;
        }
        snakerOrderService.save(orderEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCCOrder(CCOrder ccOrder) {
        CcOrderEntity ccOrderEntity = entityConvert.toCcOrderEntity(ccOrder);
        CcOrderEntity entity = snakerCcOrderService.getOne(Wrappers.<CcOrderEntity>lambdaQuery().eq(CcOrderEntity::getOrderId, ccOrder.getOrderId()));
        if (Objects.nonNull(entity)) {
            snakerCcOrderService.update(Wrappers.<CcOrderEntity>lambdaUpdate()
                    .setEntity(ccOrderEntity)
                    .eq(CcOrderEntity::getOrderId,ccOrder.getOrderId()));
            return;
        }
        snakerCcOrderService.save(ccOrderEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveProcess(Process process) {
        ProcessEntity processEntity = entityConvert.toProcessEntity(process);
        Process dbProcess = snakerProcessService.getProcessById(process.getId());
        if (Objects.nonNull(dbProcess)) {
            snakerProcessService.updateById(processEntity);
            return;
        }
        snakerProcessService.save(processEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveTaskActor(TaskActor taskActor) {
        TaskActorEntity taskActorEntity = entityConvert.toTaskActorEntity(taskActor);
        TaskActorEntity actorEntity = getSnakerTaskActorService().getOne(Wrappers.<TaskActorEntity>lambdaQuery().eq(TaskActorEntity::getTaskId, taskActor.getTaskId()));
        if (Objects.nonNull(actorEntity)) {
            snakerTaskActorService.update(Wrappers.<TaskActorEntity>lambdaUpdate()
                    .setEntity(taskActorEntity)
                    .eq(TaskActorEntity::getTaskId, taskActor.getTaskId()));
            return;
        }
        snakerTaskActorService.save(taskActorEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTask(Task task) {
        TaskEntity taskEntity = entityConvert.toTaskEntity(task);
        Task dbTask = snakerTaskService.getTaskById(task.getId());
        if (Objects.nonNull(dbTask)) {
            snakerTaskService.updateById(taskEntity);
            return;
        }
        snakerTaskService.save(taskEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOrder(Order order) {
        OrderEntity orderEntity = entityConvert.toOrderEntity(order);
        snakerOrderService.updateById(orderEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCCOrder(CCOrder ccOrder) {
        CcOrderEntity ccOrderEntity = entityConvert.toCcOrderEntity(ccOrder);
        snakerCcOrderService.updateById(ccOrderEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProcess(Process process) {
        ProcessEntity processEntity = entityConvert.toProcessEntity(process);
        snakerProcessService.updateById(processEntity);
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
        HistOrderEntity histOrderEntity = entityConvert.toHistOrderEntity(historyOrder);
        HistOrderEntity entity = snakerHistOrderService.getById(historyOrder.getId());
        if (Objects.nonNull(entity)) {
            snakerHistOrderService.updateById(histOrderEntity);
            return;
        }
        snakerHistOrderService.save(histOrderEntity);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateHistory(HistoryOrder historyOrder) {
        HistOrderEntity histOrderEntity = entityConvert.toHistOrderEntity(historyOrder);
        snakerHistOrderService.updateById(histOrderEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveHistory(HistoryTask historyTask) {
        HistTaskEntity histTaskEntity = entityConvert.toHistTaskEntity(historyTask);
        HistTaskEntity entity = snakerHistTaskService.getById(historyTask.getId());
        if (Objects.nonNull(entity)) {
            snakerHistTaskService.updateById(histTaskEntity);
            return;
        }
        snakerHistTaskService.save(histTaskEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteHistoryOrder(HistoryOrder historyOrder) {
        snakerHistOrderService.removeById(historyOrder.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteHistoryTask(HistoryTask historyTask) {
        histTaskActorService.removeById(historyTask.getId());
        snakerHistTaskService.removeById(historyTask.getId());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOrderVariable(Order order) {
        updateOrder(order);
        HistoryOrder hist = getHistOrder(order.getId());
        hist.setVariable(order.getVariable());
        updateHistory(hist);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSurrogate(Surrogate surrogate) {
        SurrogateEntity surrogateEntity = entityConvert.toSurrogateEntity(surrogate);
        Surrogate entity = snakerSurrogateService.getSurrogateById(surrogate.getId());
        if (Objects.nonNull(entity)) {
            snakerSurrogateService.updateById(surrogateEntity);
            return;
        }
        snakerSurrogateService.save(surrogateEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSurrogate(Surrogate surrogate) {
        SurrogateEntity surrogateEntity = entityConvert.toSurrogateEntity(surrogate);
        snakerSurrogateService.updateById(surrogateEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSurrogate(Surrogate surrogate) {
         snakerSurrogateService.removeById(surrogate.getId());
    }

    @Override
    public Surrogate getSurrogate(String id) {
        return snakerSurrogateService.getSurrogateById(id);
    }

    @Override
    public List<Surrogate> getSurrogate(Page<Surrogate> page, QueryFilter filter) {
        if (Objects.isNull(page)) {
            return snakerSurrogateService.getSurrogates(filter);
        }
        MpPage<Surrogate> mpPage = PageUtils.convertToMpPage(page);
        mpPage = snakerSurrogateService.getSurrogates(mpPage, filter);
        PageUtils.convertToPage(page, mpPage);
        return mpPage.getRecords();
    }


    @Override
    public Task getTask(String id) {
        return snakerTaskService.getTaskById(id);
    }

    @Override
    public HistoryTask getHistTask(String id) {
        HistTaskEntity histTaskEntity = snakerHistTaskService.getById(id);
        return entityConvert.toHistoryTask(histTaskEntity);
    }

    @Override
    public List<Task> getNextActiveTasks(String parentTaskId) {
        return snakerTaskService.getNextActiveTasks(parentTaskId);
    }

    @Override
    public List<Task> getNextActiveTasks(String orderId, String taskName, String parentTaskId) {
        return snakerTaskService.getNextActiveTasks(orderId, taskName, parentTaskId);
    }

    @Override
    public List<TaskActor> getTaskActorsByTaskId(String id) {
        return snakerTaskActorService.getTaskActorsByTaskId(id);
    }

    @Override
    public List<HistoryTaskActor> getHistTaskActorsByTaskId(String taskId) {
        return histTaskActorService.getHistTaskActorsByTaskId(taskId);
    }

    @Override
    public Order getOrder(String orderId) {
        return snakerOrderService.getOrderById(orderId);
    }

    @Override
    public List<CCOrder> getCCOrder(String orderId, String... actorIds) {
        List<String> actorIdList =  Arrays.asList(actorIds);
        return snakerCcOrderService.getCCOrder(orderId, actorIdList);
    }

    @Override
    public HistoryOrder getHistOrder(String orderId) {
        HistOrderEntity histOrderEntity = snakerHistOrderService.getById(orderId);
        return entityConvert.toHistOrder(histOrderEntity);

    }

    @Override
    public Process getProcess(String id) {
        return snakerProcessService.getProcessById(id);
    }

    @Override
    public Integer getLatestProcessVersion(String name) {
        return snakerProcessService.findLatestProcessVersionByName(name);

    }

    @Override
    public List<Process> getProcesss(Page<Process> page, QueryFilter filter) {
        if (Objects.isNull(page)) {
            return snakerProcessService.findList(filter);
        }
        MpPage<Process> mpPage = PageUtils.convertToMpPage(page);
        mpPage = snakerProcessService.findList(mpPage, filter);
        PageUtils.convertToPage(page, mpPage);
        return mpPage.getRecords();
    }

    @Override
    public List<Order> getActiveOrders(Page<Order> page, QueryFilter filter) {
        if (Objects.isNull(page)) {
            return snakerOrderService.getActiveOrders(filter);
        }
        MpPage<Order> mpPage = PageUtils.convertToMpPage(page);
        mpPage = snakerOrderService.getActiveOrders(mpPage, filter);
        PageUtils.convertToPage(page, mpPage);
        return mpPage.getRecords();
    }

    @Override
    public List<Task> getActiveTasks(Page<Task> page, QueryFilter filter) {
        if (Objects.isNull(page)) {
           return snakerTaskService.getActiveTasks(filter);
        }

        MpPage<Task> mpPage = PageUtils.convertToMpPage(page);
        mpPage = snakerTaskService.getActiveTasks(mpPage, filter);
        PageUtils.convertToPage(page, mpPage);
        return mpPage.getRecords();
    }



    @Override
    public List<HistoryOrder> getHistoryOrders(Page<HistoryOrder> page, QueryFilter filter) {
        if (Objects.isNull(page)) {
            return snakerHistOrderService.getHistoryOrders(filter);
        }
        MpPage<HistoryOrder> mpPage = PageUtils.convertToMpPage(page);
        mpPage = snakerHistOrderService.getHistoryOrders(mpPage, filter);
        PageUtils.convertToPage(page, mpPage);
        return mpPage.getRecords();
    }

    @Override
    public List<HistoryTask> getHistoryTasks(Page<HistoryTask> page, QueryFilter filter) {
        if (Objects.isNull(page)) {
            return snakerHistTaskService.getHistoryTasks(filter);
        }
        MpPage<HistoryTask> mpPage = PageUtils.convertToMpPage(page);
        snakerHistTaskService.getHistoryTasks(mpPage, filter);
        PageUtils.convertToPage(page, mpPage);
        return mpPage.getRecords();
    }

    @Override
    public List<WorkItem> getWorkItems(Page<WorkItem> page, QueryFilter filter) {
        if (Objects.isNull(page)) {
            return snakerTaskService.getWorkItems(filter);
        }
         MpPage<WorkItem> mpPage = PageUtils.convertToMpPage(page);
         mpPage = snakerTaskService.getWorkItems(mpPage, filter);
         PageUtils.convertToPage(page, mpPage);
        return mpPage.getRecords();
    }

    @Override
    public List<HistoryOrder> getCCWorks(Page<HistoryOrder> page, QueryFilter filter) {
        if (Objects.isNull(page)) {
            return snakerHistOrderService.getCCWorks(filter);
        }
        MpPage<HistoryOrder> mpPage = PageUtils.convertToMpPage(page);
        mpPage = snakerHistOrderService.getCCWorks(mpPage, filter);
        PageUtils.convertToPage(page, mpPage);
        return mpPage.getRecords();
    }

    @Override
    public List<WorkItem> getHistoryWorkItems(Page<WorkItem> page, QueryFilter filter) {
        if (Objects.isNull(page)) {
            return snakerHistTaskService.getHistoryWorkItems(filter);
        }
        MpPage<WorkItem> mpPage = PageUtils.convertToMpPage(page);
        mpPage = snakerHistTaskService.getHistoryWorkItems(mpPage, filter);
        PageUtils.convertToPage(page, mpPage);
        return mpPage.getRecords();
    }

    @Override
    public <T> T queryObject(Class<T> clazz, String sql, Object... objects) {
        return null;
    }

    @Override
    public <T> List<T> queryList(Class<T> clazz, String sql, Object... objects) {
        return null;
    }

    @Override
    public <T> List<T> queryList(Page<T> page, QueryFilter queryFilter, Class<T> clazz, String s, Object... objects) {
        return null;
    }



    @Override
    public void runScript() {
        if (!properties.isAutoInitSchema()) {
            return;
        }
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            if(JdbcHelper.isExec(conn)) {
                log.info("script has completed execution.skip this step");
                return;
            }
            String databaseType = JdbcHelper.getDatabaseType(conn);
            String schema = "db/schema-" + databaseType + ".sql";
            ScriptRunner runner = new ScriptRunner(conn, true);
            runner.runScript(schema);
        } catch (Exception e) {
            throw new SnakerException(e);
        } finally {
            try {
                JdbcHelper.close(conn);
            } catch (SQLException e) {
                //ignore
            }
        }
    }
}
