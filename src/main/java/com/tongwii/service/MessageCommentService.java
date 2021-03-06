package com.tongwii.service;

import com.tongwii.dao.IMessageCommentDao;
import com.tongwii.domain.MessageComment;
import com.tongwii.dto.MessageCommentDTO;
import com.tongwii.dto.mapper.MessageCommentMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by admin on 2017/10/23.
 */
@Service
@Transactional
public class MessageCommentService {

    private final IMessageCommentDao messageCommentDao;
    private final MessageCommentMapper messageCommentMapper;

    public MessageCommentService(IMessageCommentDao messageCommentDao, MessageCommentMapper messageCommentMapper) {
        this.messageCommentDao = messageCommentDao;
        this.messageCommentMapper = messageCommentMapper;
    }
    /**
     * 根据messageId获取点赞评论记录
     * @param messageId 消息id
     */
    public List<MessageCommentDTO> findByMessageIdAndType(String messageId, int type){
        return Optional.ofNullable(messageCommentDao.findByMessageIdAndType(messageId, type)).orElse(new ArrayList<>()).stream().map(messageCommentMapper::toDto).collect(Collectors.toList());
    }


    /**
     * 根据消息id和类型获取评论数量
     *
     * @param messageId 消息id
     * @param type type
     * @return
     */
    public int getCountByMessageIdAndType(String messageId, int type) {
        return messageCommentDao.countByMessageIdAndType(messageId, type);
    }

    /**
     * 根据messageId与commentorId查询
     */
    public List<MessageComment> findByMessageIdAndCommentatorIdAndType(String messageId, String commentatorId, int type){
        return messageCommentDao.findByMessageIdAndCommentatorIdAndType(messageId, commentatorId, type);
    }

    /**
     * 添加点赞评论记录
     */
    public void addMessageComment(MessageComment messageComment){
        messageCommentDao.save(messageComment);
    }

    public int getCommentCounts(String id) {
       return messageCommentDao.countByMessageId(id);
    }

    public Page<MessageCommentDTO> findAllByMessageId(String messageId, Pageable pageable) {
        return messageCommentDao.findAllByMessageId(messageId, pageable).map(messageCommentMapper::toDto);
    }

    /**
     * Save a messageComment.
     *
     * @param messageCommentDTO the entity to save
     * @return the persisted entity
     */
    public MessageCommentDTO save(MessageCommentDTO messageCommentDTO) {
        MessageComment messageComment = messageCommentMapper.toEntity(messageCommentDTO);
        messageComment = messageCommentDao.save(messageComment);
        return messageCommentMapper.toDto(messageComment);
    }


    /**
     * Get all the messageComments.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<MessageCommentDTO> findAll(Pageable pageable) {
        return messageCommentDao.findAll(pageable)
            .map(messageCommentMapper::toDto);
    }

    /**
     * Get one messageComment by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public MessageCommentDTO findOne(String id) {
        MessageComment messageComment = messageCommentDao.findOne(id);
        return messageCommentMapper.toDto(messageComment);
    }

    /**
     * Delete the messageComment by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        messageCommentDao.delete(id);
    }

}
