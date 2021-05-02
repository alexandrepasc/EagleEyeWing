package com.eagleeye.wing.dao;

import com.eagleeye.wing.models.NotificationDbModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificationDbDao extends CrudRepository<NotificationDbModel, UUID> {
}
