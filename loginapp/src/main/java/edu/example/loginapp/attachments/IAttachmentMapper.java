package edu.example.loginapp.attachments;

import org.mapstruct.Mapper;

import edu.example.loginapp.attachments.entities.Attachment;
import edu.example.loginapp.attachments.entities.dto.AttachmentDTO;

@Mapper
public interface IAttachmentMapper {

    Attachment fromDtoToAttachment(AttachmentDTO source);

    AttachmentDTO fromAttachmentToDto(Attachment source);
}
