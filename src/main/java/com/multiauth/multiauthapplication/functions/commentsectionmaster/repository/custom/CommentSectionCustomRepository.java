package com.multiauth.multiauthapplication.functions.commentsectionmaster.repository.custom;

import com.multiauth.multiauthapplication.functions.commentsectionmaster.dto.CommentSectionDto;

public interface CommentSectionCustomRepository {

    void addNewComment(CommentSectionDto commentSectionDto);
}
