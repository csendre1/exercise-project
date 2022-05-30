package edu.example.loginapp.attachments;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.example.loginapp.attachments.entities.Attachment;
import edu.example.loginapp.attachments.entities.dto.AttachmentDTO;

public interface IAttachmentService {

    Attachment save(MultipartFile file) throws IOException;

    List<Attachment> getAllImages();

    AttachmentDTO findUserImage(String username);
}
