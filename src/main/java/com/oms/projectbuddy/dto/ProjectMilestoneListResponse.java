/**
 * 
 */
package com.oms.projectbuddy.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pradeep
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectMilestoneListResponse {
	

    private Long id;

    private Long milestoneId;
    private String projectId;
    
    private String milestoneType;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @ApiModelProperty(value = "Date in yyyy-MM-dd format")
    private LocalDate date;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate createdDate;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate updatedDate;
    
}
