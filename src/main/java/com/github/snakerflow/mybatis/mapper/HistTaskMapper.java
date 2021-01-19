package com.github.snakerflow.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.snakerflow.mybatis.entity.HistTaskEntity;
import com.github.snakerflow.util.MpPage;
import org.apache.ibatis.annotations.Param;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.WorkItem;

import java.util.List;

/**
 * @author zhaocheng
 */

public interface HistTaskMapper extends BaseMapper<HistTaskEntity> {


    /**
     * 条件查询
     * @param filter /
     * @return /
     */
    List<HistoryTask> findListPage(@Param("filter") QueryFilter filter);

    /**
     * 分页查找
     * @param page /
     * @param filter /
     * @return /
     */
    MpPage<HistoryTask> findListPage(Page<HistoryTask> page, @Param("filter") QueryFilter filter);

    /**
     * 条件查询
     * @param filter /
     * @return /
     */
    List<WorkItem> findHistoryWorkItems(@Param("filter")QueryFilter filter);
    /**
     * 分页条件查询
     * @param page /
     * @param filter /
     * @return /
     */
    MpPage<WorkItem> findHistoryWorkItems(Page<WorkItem> page, @Param("filter")QueryFilter filter);
}
