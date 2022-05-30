package edu.example.loginapp.attachments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.example.loginapp.attachments.entities.Attachment;
import edu.example.loginapp.attachments.entities.dto.AttachmentDTO;

@RestController
@RequestMapping("/attachments")
public class AttachmentController {

    @Autowired
    private IAttachmentService attachmentService;

    @GetMapping
    public List<Attachment> getImages() {
        return attachmentService.getAllImages();
    }

    @GetMapping("/{username}")
    public AttachmentDTO findByUsername(@PathVariable String username) {
        return attachmentService.findUserImage(username);
    }
}
