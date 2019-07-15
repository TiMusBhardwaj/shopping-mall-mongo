package com.example.mall.domain;
import java.util.List;

/**
 * @author sumit.bhardwaj
 *
 */
public class ApiError {
    private List<String> errors;

    public ApiError(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}