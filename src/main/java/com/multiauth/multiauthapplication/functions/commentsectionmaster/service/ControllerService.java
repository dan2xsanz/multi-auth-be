package com.multiauth.multiauthapplication.functions.commentsectionmaster.service;

import com.multiauth.multiauthapplication.functions.commentsectionmaster.dto.CommentSectionDto;

import java.util.List;


public interface ControllerService {

    void addComments(CommentSectionDto commentSectionDto);

    List<CommentSectionDto> allCommentsList(Long productMasterId);

    Long totalNumberOfComments(Long productMasterId);
}
