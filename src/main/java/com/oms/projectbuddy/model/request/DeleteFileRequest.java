package com.oms.projectbuddy.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteFileRequest {
    private Long id;
    private String userId;
    private String type;
}
