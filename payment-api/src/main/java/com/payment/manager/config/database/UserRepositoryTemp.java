package com.payment.manager.config.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payment.manager.adapter.outbound.entity.UserEntity;

public interface UserRepositoryTemp extends JpaRepository<UserEntity, Long> {

}
