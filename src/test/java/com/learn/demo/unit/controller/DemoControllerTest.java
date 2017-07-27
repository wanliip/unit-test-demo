package com.learn.demo.unit.controller;

import com.learn.demo.unit.domain.Language;
import com.learn.demo.unit.service.DemoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 类说明:
 *
 * @author 杨磊
 * @since 2017-07-25 下午6:53
 */
@RunWith(MockitoJUnitRunner.class)
public class DemoControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private DemoController controller;

    @Mock
    private DemoService demoService;

    private Language defaultEntityJava;

    private Language defaultEntityC;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
        defaultEntityC = new Language(2, "C", "C是一种通用的程式語言，广泛用于系统软件与应用软件的开发.");
        defaultEntityJava = new Language(1, "JAVA", "Java是一种广泛使用的计算机编程语言，拥有跨平台、面向对象、泛型编程的特性.");
    }

    @Test
    public void helloWorldTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/helloworld")).andExpect(status().isOk()).andReturn();
        Assert.assertEquals(result.getResponse().getContentAsString(), "hello world");
    }

    @Test
    public void getLanguageTest() throws Exception {
        when(demoService.get(anyInt())).thenReturn(defaultEntityJava);
        mockMvc.perform(get("/language/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("JAVA"));
    }

    @Test
    public void getLanguagePageResultNullTest() throws Exception {
        mockMvc.perform(get("/language/1/20"))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();
    }

    @Test
    public void getLanguagePageResultTest() throws Exception {
        List<Language> list = Arrays.asList(defaultEntityJava, defaultEntityC);
        when(demoService.list(anyInt(), anyInt())).thenReturn(list);
        mockMvc.perform(get("/language/1/20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$.[0].name").value("JAVA"))
                .andDo(print()).andReturn();
    }

    @Test
    public void saveLanguageTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(defaultEntityJava);
        when(demoService.save(anyObject())).thenReturn(1);
        MvcResult result = mockMvc.perform(post("/language").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();
        Assert.assertEquals(result.getResponse().getContentAsString(), "1");
    }

    @Test
    public void updateLanguageTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(defaultEntityJava);
        when(demoService.update(anyObject())).thenReturn(1);
        MvcResult result = mockMvc.perform(put("/language").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();
        Assert.assertEquals(result.getResponse().getContentAsString(), "1");
    }
}
