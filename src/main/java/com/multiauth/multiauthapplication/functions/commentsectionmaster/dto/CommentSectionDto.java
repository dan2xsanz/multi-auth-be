package com.multiauth.multiauthapplication.functions.commentsectionmaster.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentSectionDto {

    private Long id;

    private String comment;

    private Long productMasterId;

    private Long accountMasterId;

    private String accountMasterName;
}
