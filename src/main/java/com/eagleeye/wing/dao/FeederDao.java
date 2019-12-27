package com.eagleeye.wing.dao;

import com.eagleeye.wing.models.FeederModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FeederDao extends CrudRepository<FeederModel, UUID> {

  Optional<FeederModel> findById(UUID id);
}
