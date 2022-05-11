package edu.example.loginapp.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.example.loginapp.dto.AttachmentDTO;
import edu.example.loginapp.model.Attachment;

@Service
public interface AttachmentService {

    Attachment save(MultipartFile file) throws IOException;

    List<Attachment> getAllImages();

    AttachmentDTO findUserImage(String username);
}
