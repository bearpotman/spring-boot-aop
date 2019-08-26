package com.bpm.aop.repository;

import com.bpm.aop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bearPotMan on 2019/8/26 14:08.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByPhone(String phone);

}
