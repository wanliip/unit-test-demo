package com.learn.demo.unit.service;

import com.learn.demo.unit.domain.Language;

import java.util.List;

/**
 * 类说明: ${DESCRIPTION}
 *
 * @author wallace
 * @since 2017-07-25 14:38
 */
public interface DemoService {
    List<Language> list(Integer pageIndex, Integer pageSize);

    Language get(Integer id);

    Integer save(Language language);

    Integer update(Language language);
}
