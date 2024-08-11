package com.multiauth.multiauthapplication.functions.commentsectionmaster.dto;

import com.multiauth.multiauthapplication.common.dto.NotificationDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CommentSectionDto extends NotificationDetailsDto {

    private Long id;

    private String comment;

    private Long productMasterId;

    private Long accountMasterId;

    private String accountMasterName;
}
