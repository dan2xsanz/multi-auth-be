package com.multiauth.multiauthapplication.functions.notification.repository;

import com.multiauth.multiauthapplication.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(value = "SELECT * FROM Notification as notification " +
            "LEFT JOIN ProductMaster as pm ON notification.productMasterId = pm.id " +
            "WHERE notification.accountMasterId = :accountMasterId", nativeQuery = true)
    List<Notification> notificationByAccountMasterId(@Param("accountMasterId") Long accountMasterId);

}