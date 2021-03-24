package com.eagleeye.wing.services;

import com.eagleeye.wing.dao.NotificationDbDao;
import com.eagleeye.wing.exceptions.UsersNotFoundForFeederException;
import com.eagleeye.wing.models.FeederModel;
import com.eagleeye.wing.models.NotificationDbModel;
import com.eagleeye.wing.models.UserModel;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class NotificationDbService {

    public void createNotifications(FeederModel feeder, NotificationDbDao notificationDbDao) {

        List<UserModel> users = feeder.getUsers();

        if (users.size() < 1) {
            throw new UsersNotFoundForFeederException(feeder.getId());
        }

        for (UserModel user : users) {

            NotificationDbModel notification =
                    setNotification(feeder.getId(), feeder.getPackName(), feeder.getPackVersion(), user.getId());

            notificationDbDao.save(notification);
        }
    }

    private NotificationDbModel setNotification(
            UUID feederId, String feederName, String feederVersion, UUID userId) {

        NotificationDbModel newNotification = new NotificationDbModel();

        newNotification.setUserId(userId);
        newNotification.setTitle(feederName + " updated");
        newNotification.setText("New version " + feederVersion);
        newNotification.setUnread(true);
        newNotification.setFeederId(feederId);
        newNotification.setNotificationDate(Instant.now().toEpochMilli());

        return newNotification;
    }
}
