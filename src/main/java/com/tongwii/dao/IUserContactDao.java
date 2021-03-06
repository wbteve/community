package com.tongwii.dao;

import com.tongwii.domain.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Zeral
 * @date 2017-09-21
 */
@Repository
public interface IUserContactDao extends JpaRepository<UserContact, String> {
    List<UserContact> findByUserId(String userId);
}
