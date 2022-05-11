package edu.example.loginapp.services.impl;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.example.loginapp.dto.AttachmentDTO;
import edu.example.loginapp.model.Attachment;
import edu.example.loginapp.model.AuthUser;
import edu.example.loginapp.repositories.IAttachmentRepository;
import edu.example.loginapp.repositories.IUserRepository;
import edu.example.loginapp.services.AttachmentService;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private IAttachmentRepository attachmentRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    @Transactional
    public Attachment save(MultipartFile file) throws IOException {
        try {
            return attachmentRepository.save(createAttachmentFromFile(file));
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Error saving the attachment in the database.");
        }
    }

    @Override
    public List<Attachment> getAllImages() {
        return attachmentRepository.findAll();
    }

    @Override
    @Transactional
    public AttachmentDTO findUserImage(String username) {
        AuthUser user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found with!"));

        Attachment userAttachment = user.getProfilePicture();

        return userAttachment != null ? mapAttachmentToAttachmentDTO(userAttachment) : new AttachmentDTO();
    }

    private AttachmentDTO mapAttachmentToAttachmentDTO(Attachment attachment) {
        String data = Base64.getEncoder().encodeToString(attachment.getData());
        return AttachmentDTO.builder()
                .name(attachment.getName())
                .type(attachment.getType())
                .data(data)
                .build();

    }

    private Attachment createAttachmentFromFile(MultipartFile file) throws IOException {
        return Attachment.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .data(file.getBytes())
                .build();
    }

}