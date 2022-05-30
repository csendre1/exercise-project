package edu.example.loginapp.attachments;

public class AttachmentServiceException extends RuntimeException {

    public AttachmentServiceException() {
        super();
    }

    public AttachmentServiceException(final String msg) {
        super(msg);
    }
}
