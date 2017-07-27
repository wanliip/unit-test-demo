package com.learn.demo.unit.service;

import com.learn.demo.unit.dao.DemoDao;
import com.learn.demo.unit.domain.Language;
import com.learn.demo.unit.service.impl.DemoServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

/**
 * 类说明:
 *
 * @author 杨磊
 * @since 2017-07-27 下午2:13
 */
@RunWith(MockitoJUnitRunner.class)
public class DemoServiceTest {
    @InjectMocks
    private DemoServiceImpl service;
    @Mock
    private DemoDao dao;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Language defaultEntityJava;

    private Language defaultEntityC;

    @Before
    public void setUp() {
        defaultEntityC = new Language(2, "C", "C是一种通用的程式語言，广泛用于系统软件与应用软件的开发.");
        defaultEntityJava = new Language(1, "JAVA", "Java是一种广泛使用的计算机编程语言，拥有跨平台、面向对象、泛型编程的特性.");
    }

    @Test
    public void listTest() {
        List<Language> page = Arrays.asList(defaultEntityJava, defaultEntityC);
        when(dao.queryByPage(anyInt(), anyInt())).thenReturn(page);
        Assert.assertThat(service.list(anyInt(), anyInt()).size(), is(2));
    }

    @Test
    public void getTest() {
        when(dao.queryById(anyInt())).thenReturn(defaultEntityJava);
        Assert.assertThat(service.get(anyInt()).getName(), is("JAVA"));
    }
    @Test
    public void getNullTest(){
        when(dao.queryById(anyInt())).thenReturn(null);
        expectedException.expectMessage("该语言不存在,id:1");
        service.get(1);
    }

    @Test
    public void saveTest() {
        when(dao.insert(anyObject())).thenReturn(1);
        Assert.assertThat(service.save(anyObject()), is(1));
    }

    @Test
    public void updateTest() {
        when(dao.update(anyObject())).thenReturn(1);
        Assert.assertThat(service.update(anyObject()), is(1));
    }
}
