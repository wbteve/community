package com.tongwii.dao;

import com.tongwii.domain.MessageComment;
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
public interface IMessageCommentDao extends JpaRepository<MessageComment, String> {

    // 根据messageId查询消息评论记录
    List<MessageComment> findByMessageIdAndType(String messageId, Integer type);

    // 根据messageId与commentatorId查询消息点赞评论记录
    List<MessageComment> findByMessageIdAndCommentatorIdAndType(String messageId, String commentatorId, Integer type);
}
