package com.liuyang.test.controller.config.validate;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO

 * @Createed Date: 2018/6/13-下午7:59

 **/
@Data
public class ValidationErrorDTO {
    private List<FieldErrorDTO> fieldErrors;
 
    public ValidationErrorDTO() {
        fieldErrors = new ArrayList<>();
    }
 
    public void addFieldError(String path, String message) {
        FieldErrorDTO error = new FieldErrorDTO(path, message);
        fieldErrors.add(error);
    }
}