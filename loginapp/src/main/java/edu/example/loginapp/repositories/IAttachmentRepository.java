package edu.example.loginapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.example.loginapp.model.Attachment;

public interface IAttachmentRepository extends JpaRepository<Attachment, Long> {

}
