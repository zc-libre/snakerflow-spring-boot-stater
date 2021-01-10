package com.github.snakerflow.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.snakerflow.mybatis.entity.HistTaskEntity;
import org.apache.ibatis.annotations.Param;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.WorkItem;

import java.util.List;

/**
 * @author zhaocheng
 */

public interface HistTaskMapper extends BaseMapper<HistTaskEntity> {

    List<HistoryTask> findListPage(Page<HistoryTask> page, @Param("filter") QueryFilter filter);

    List<WorkItem> findHistoryWorkItems(Page<WorkItem> page, @Param("filter")QueryFilter filter);
}
