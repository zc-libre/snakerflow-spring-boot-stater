package com.github.snakerflow.util;

import org.snaker.engine.access.Page;

import java.util.Objects;

/**
 * @author zhao.cheng
 * @date 2021/1/13 14:04
 */
public class PageUtils {

    public static <T> MpPage<T> convertToMpPage(Page<T> page) {

        MpPage<T> mpPage = new MpPage<>();
        mpPage.setCurrent(page.getPageNo());
        mpPage.setSize(page.getPageSize());
        return mpPage;
    }

    public static <T> Page<T> convertToPage(Page<T> page, MpPage<T> mpPage) {
        page.setResult(mpPage.getRecords());
        page.setTotalCount(mpPage.getTotal());
        return page;
    }
}
