package com.multiauth.multiauthapplication.functions.commentsectionmaster.service.impl;

import com.multiauth.multiauthapplication.common.enums.NotificationEnum;
import com.multiauth.multiauthapplication.functions.commentsectionmaster.dto.CommentSectionDto;
import com.multiauth.multiauthapplication.functions.commentsectionmaster.repository.CommentSectionMasterRepository;
import com.multiauth.multiauthapplication.functions.commentsectionmaster.repository.custom.CommentSectionCustomRepository;
import com.multiauth.multiauthapplication.functions.commentsectionmaster.service.ControllerService;
import com.multiauth.multiauthapplication.functions.notification.dto.NotificationDto;
import com.multiauth.multiauthapplication.functions.notification.service.NotificationService;
import com.multiauth.multiauthapplication.model.CommentSectionMaster;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ControllerServiceImpl implements ControllerService {

    @Autowired
    private CommentSectionCustomRepository commentSectionCustomRepository;

    @Autowired
    private CommentSectionMasterRepository commentSectionMasterRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public void addComments(CommentSectionDto commentSectionDto) {

        commentSectionCustomRepository.addNewComment(commentSectionDto);

        // GENERATE NOTIFICATION
        NotificationDto notificationDto = new NotificationDto();

        notificationDto.setRead(false);
        notificationDto.setAccountMasterId(commentSectionDto.getAccountMasterId());
        notificationDto.setProductMasterId(commentSectionDto.getProductMasterId());
        notificationDto.setNotifiedAccountMasterId(commentSectionDto.getNotifiedAccountMasterId());
        notificationDto.setNotificationTopic(NotificationEnum.COMMENT.getNotificationEnum());

        notificationService.generateNotification(notificationDto);
    }

    @Override
    public List<CommentSectionDto> allCommentsList(Long productMasterId) {

        List<CommentSectionDto> commentSectionDtoListResponse = new ArrayList<>();

        List<CommentSectionMaster> commentSectionMasterList = commentSectionMasterRepository
                .findAllCommentsByProductMasterId(productMasterId);

        if (!CollectionUtils.isEmpty(commentSectionMasterList)) {
            for (CommentSectionMaster commentSectionMaster : commentSectionMasterList) {

                CommentSectionDto commentSectionDto = new CommentSectionDto();
                BeanUtils.copyProperties(commentSectionMaster, commentSectionDto);

                // COMPLETE NAME
                String firstName = commentSectionMaster.getAccountMaster().getFirstName();
                String lastName = commentSectionMaster.getAccountMaster().getLastName();
                commentSectionDto.setAccountMasterName(String.format("%s %s", firstName, lastName));

                // SET ACCOUNT MASTER ID
                commentSectionDto.setAccountMasterId(commentSectionMaster.getAccountMaster().getId());

                // SET PRODUCT MASTER ID
                commentSectionDto.setProductMasterId(commentSectionMaster.getProductMaster().getId());

                commentSectionDtoListResponse.add(commentSectionDto);
            }
        }

        return commentSectionDtoListResponse;
    }

    @Override
    public Long totalNumberOfComments(Long productMasterId) {

        return commentSectionMasterRepository.countAllCommentsByProductMasterId(productMasterId);

    }
}
