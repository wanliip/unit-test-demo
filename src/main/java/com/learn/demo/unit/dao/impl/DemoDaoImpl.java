package com.learn.demo.unit.dao.impl;

import com.alibaba.dubbo.common.utils.Assert;
import com.learn.demo.unit.dao.AbstractDao;
import com.learn.demo.unit.dao.DemoDao;
import com.learn.demo.unit.domain.Language;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 类说明:
 *
 * @author 杨磊
 * @since 2017-07-25 下午2:47
 */
@Repository
@Slf4j
public class DemoDaoImpl extends AbstractDao implements DemoDao {
    @Override
    public Language queryById(Integer id) {
        Language language = null;
        String sql = "select id , `name` , description ,create_time as createTime ,update_time as updateTime from t_language where id = ?";
        try {
            language = jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Language>(Language.class));
        } catch (EmptyResultDataAccessException e) {
            log.error("language id: not exist!", id, e);
        }
        return language;
    }

    @Override
    public List<Language> queryByPage(Integer pageIndex, Integer pageSize) {
        StringBuilder sql = new StringBuilder("select id , `name` , description ,create_time as createTime ,update_time as updateTime from t_language ");
        List<Object> params = new ArrayList<>();
        page(pageIndex, pageSize, sql, params);
        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<Language>(Language.class));
    }

    @Override
    public Integer insert(Language language) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("t_language")
                .usingColumns("name", "description").usingGeneratedKeyColumns("id");
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(language);
        return insert.executeAndReturnKey(params).intValue();
    }

    @Override
    public Integer update(Language language) {
        Assert.notNull(language.getId(), "语言ID不能为空");
        StringBuilder sql = new StringBuilder(" update t_language set update_time = ?, ");
        List<Object> params = new ArrayList<>();
        params.add(new Date());
        Optional.ofNullable(language.getName()).ifPresent(
                name -> updateField(sql, params, name, " name = ?, ")
        );
        Optional.ofNullable(language.getDescription()).ifPresent(
                description -> updateField(sql, params, description, " description = ? ")
        );
        sql.append(" where id = ? ");
        params.add(language.getId());
        return jdbcTemplate.update(sql.toString(), params.toArray());
    }
}
