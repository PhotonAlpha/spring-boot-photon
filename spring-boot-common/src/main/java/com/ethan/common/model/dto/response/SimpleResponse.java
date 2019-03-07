package com.ethan.common.model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @version 1.0
 * @date 07/03/2019
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleResponse<T> {
    private Boolean success;
    private String message;
    private HttpStatus status;
    private T data;

    public SimpleResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
