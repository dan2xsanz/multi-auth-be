package com.multiauth.multiauthapplication.util.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindByPropertyDto {

    private String tableName;

    private String tableProperty;

    private String tablePropertyValue;

    private String selectedTableProperty;

}
