package com.learn.demo.unit.dao;

import com.learn.demo.unit.dao.impl.DemoDaoImpl;
import com.learn.demo.unit.domain.Language;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import static org.hamcrest.CoreMatchers.is;

/**
 * 类说明:
 *
 * @author 杨磊
 * @since 2017-07-27 下午2:59
 */
@RunWith(MockitoJUnitRunner.class)
public class DemoDaoTest {
    @InjectMocks
    DemoDaoImpl dao;

    private EmbeddedDatabase database;

    @Before
    public void setUp() {
        database = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("db-compatibility-mode.sql")
                .addScript("db-schema.sql")
                .addScript("db-test-data.sql")
                .build();
        dao.jdbcTemplate = new JdbcTemplate(database);
    }

    @After
    public void setTear() {
        database.shutdown();
    }


    @Test
    public void testQueryByIdTest() {
        Assert.assertThat(dao.queryById(1).getName(), is("JAVA"));
    }

    @Test
    public void testQueryByPage() {
        Assert.assertThat(dao.queryByPage(1, 20).size(), is(2));
    }

    @Test
    public void testInsert() {
        Assert.assertThat(dao.insert(new Language(3, "PHP", "PHP是一种开源的通用计算机脚本语言")), is(3));
        Assert.assertThat(dao.queryById(3).getName(), is("PHP"));
    }

    @Test
    public void testUpdate() {
        Assert.assertThat(dao.update(new Language(1, "java", "Java是一种广泛使用的计算机编程语言，拥有跨平台、面向对象、泛型编程的特性.")), is(1));
        Assert.assertThat(dao.queryById(1).getName(), is("java"));
    }
}
