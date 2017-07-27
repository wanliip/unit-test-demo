package com.learn.demo.unit.dao;

import com.learn.demo.unit.domain.Language;

import java.util.List;

/**
 * 类说明: ${DESCRIPTION}
 *
 * @author wallace
 * @since 2017-07-25 14:45
 */
public interface DemoDao {
    Language queryById(Integer id);

    List<Language> queryByPage(Integer pageIndex, Integer pageSize);

    Integer insert(Language language);

    Integer update(Language language);
}
