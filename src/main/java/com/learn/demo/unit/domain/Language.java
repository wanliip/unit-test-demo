package com.learn.demo.unit.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 类说明:
 *
 * @author 杨磊
 * @since 2017-07-25 下午2:40
 */
@Getter
@Setter
@ToString
public class Language {
    private Integer id;
    private String name;
    private String description;

    public Language() {
        //default constructor for jdbc template mapping
    }

    public Language(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
