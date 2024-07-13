package com.multiauth.multiauthapplication.functions.commentsectionmaster.controller;

import com.multiauth.multiauthapplication.functions.commentsectionmaster.dto.CommentByProductDto;
import com.multiauth.multiauthapplication.functions.commentsectionmaster.dto.CommentSectionDto;
import com.multiauth.multiauthapplication.functions.commentsectionmaster.service.ControllerService;
import com.multiauth.multiauthapplication.model.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("master-record/comment-section")
public class CommentSectionController {

    @Autowired
    private ControllerService controllerService;

    @PostMapping("add-comment")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel addComment(@RequestBody CommentSectionDto commentSectionDto) {
        controllerService.addComments(commentSectionDto);
        return ApiResultModel.builder()
                .isSuccess(true)
                .build();
    }

    @PostMapping("comments-by-product")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel getAllComments(@RequestBody CommentByProductDto commentByProductDto) {
        return ApiResultModel.builder()
                .isSuccess(true)
                .resultData(controllerService.allCommentsList(commentByProductDto.getProductMasterId()))
                .build();
    }

    @PostMapping("total-comments-by-product")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel getTotalComments(@RequestBody CommentByProductDto commentByProductDto) {
        return ApiResultModel.builder()
                .isSuccess(true)
                .resultData(controllerService.totalNumberOfComments(commentByProductDto.getProductMasterId()))
                .build();
    }
}