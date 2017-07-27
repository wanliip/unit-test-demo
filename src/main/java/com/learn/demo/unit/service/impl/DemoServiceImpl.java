package com.learn.demo.unit.service.impl;

import com.learn.demo.unit.dao.DemoDao;
import com.learn.demo.unit.domain.Language;
import com.learn.demo.unit.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类说明:
 *
 * @author 杨磊
 * @since 2017-07-25 下午2:44
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoDao dao;

    @Override
    public List<Language> list(Integer pageIndex, Integer pageSize) {
        return dao.queryByPage(pageIndex, pageSize);
    }

    @Override
    public Language get(Integer id) {
        Language language = dao.queryById(id);
        if (language == null) {
            throw new RuntimeException("该语言不存在,id:" + id);
        }
        return language;
    }

    @Override
    public Integer save(Language language) {
        return dao.insert(language);
    }

    @Override
    public Integer update(Language language) {
        return dao.update(language);
    }
}
