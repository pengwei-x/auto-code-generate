
package com.liuyang.test.controller.config.validate;

import lombok.Data;

/**
 * @Description: TODO

 * @Createed Date: 2018/6/13-下午8:05

 **/
@Data
public class FieldErrorDTO {
 
    private String field;
 
    private String message;
 
    public FieldErrorDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }
}