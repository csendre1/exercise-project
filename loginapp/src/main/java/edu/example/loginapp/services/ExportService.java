package edu.example.loginapp.services;

import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public interface ExportService {

    void export(Long id) throws IOException;
}
