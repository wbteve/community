package com.tongwii.dto.mapper;

import com.tongwii.domain.Message;
import com.tongwii.dto.NeighborMessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by admin on 2017/10/24.
 */
@Service
public class NeighborMessageMapper {
    NeighborMessageDTO toDto(Message message) {
        if ( message == null ) {
            return null;
        }

        NeighborMessageDTO neighborMessageDTO = new NeighborMessageDTO();
        neighborMessageDTO.setId(message.getId());
        neighborMessageDTO.setTitle(message.getTitle());
        neighborMessageDTO.setContent(message.getContent());
        neighborMessageDTO.setCreateTime(message.getCreateTime());
        neighborMessageDTO.setCreateUser(message.getCreateUser().getAccount());
        neighborMessageDTO.setCreateUserAvatar(Objects.nonNull(message.getCreateUser().getAvatarFile()) ? message.getCreateUser().getAvatarFile().getFilePath() : null);
        neighborMessageDTO.setMessageType(message.getMessageType());
        return neighborMessageDTO;
    }


    public List<NeighborMessageDTO> toDtos(List<Message> messages) {
        return messages.stream()
            .filter(Objects::nonNull)
            .map(this::toDto)
            .collect(Collectors.toList());
    }
}
