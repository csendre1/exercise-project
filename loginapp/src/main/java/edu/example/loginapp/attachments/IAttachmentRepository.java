package edu.example.loginapp.attachments;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.example.loginapp.attachments.entities.Attachment;

public interface IAttachmentRepository extends JpaRepository<Attachment, Long> {

}
