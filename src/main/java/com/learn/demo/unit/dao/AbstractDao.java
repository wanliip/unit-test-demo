package com.learn.demo.unit.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

/**
 * 类说明: Dao   类
 *
 * @author 杨磊
 * @since  2017-05-22 下午1:48
 */
public abstract class AbstractDao {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    /**
     * 解析  页数
     *
     * @param pageIndex 页
     * @param pageSize  页   默认 10
     * @return pageIndex
     */
    protected int parsePageIndex(int pageIndex, int pageSize) {
        int start;
        if (pageIndex <= 1) {
            start = 0;
        } else {
            start = pageIndex - 1;
        }
        return start * pageSize;
    }

    /**
     * 装sql语句 参数
     *
     * @param pageIndex 页
     * @param pageSize  页
     * @param sql       SQL
     * @param paramList 参数列表
     */
    protected void page(int pageIndex, int pageSize, StringBuilder sql, List<Object> paramList) {
        if (pageSize > 0) {
            sql.append(" LIMIT ?, ?");
            paramList.add(parsePageIndex(pageIndex, pageSize));
            paramList.add(pageSize);
        }
    }

    /**
     * 动 更新 段
     *
     * @param sql       sql语句
     * @param paramList 参数列表
     * @param obj       更新值
     * @param sqlField  段
     */
    protected void updateField(StringBuilder sql, List<Object> paramList, Object obj, String sqlField) {
        Optional.ofNullable(obj).ifPresent(value -> {
            sql.append(sqlField);
            paramList.add(value);
        });
    }
}
