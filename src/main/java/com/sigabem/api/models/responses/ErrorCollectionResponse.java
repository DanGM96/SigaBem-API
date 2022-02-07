package com.sigabem.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorCollectionResponse {
    private int code;
    private String errorMessage;
    private List<String> errorList;
}
