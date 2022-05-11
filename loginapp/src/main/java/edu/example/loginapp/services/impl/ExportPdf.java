package edu.example.loginapp.services.impl;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.example.loginapp.model.Product;
import edu.example.loginapp.repositories.IProductRepository;
import edu.example.loginapp.services.ExportService;

@Service
public class ExportPdf implements ExportService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public void export(Long id) throws IOException {
        createPDFDocument(id);
    }

    private void createPDFDocument(Long id) throws IOException {

        String title = "Product detail";

        PDDocument newDocument = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        newDocument.addPage(page);
        PDFont font = PDType1Font.HELVETICA_BOLD;

        PDRectangle mediaBox = page.getMediaBox();
        newDocument.addPage(page);
        int marginTop = 30;
        int fontSize = 16;
        Product product = loadProduct(id);
        PDPageContentStream contentStream = new PDPageContentStream(newDocument, page);

        float titleWidth = font.getStringWidth(title) / 1000 * fontSize;
        float titleHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;

        float startX = (mediaBox.getWidth() - titleWidth) / 2;
        float startY = mediaBox.getHeight() - marginTop - titleHeight;

        contentStream.setFont(PDType1Font.COURIER, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(startX, startY);
        contentStream.newLineAtOffset(50, 60);
        contentStream.showText("Serial number: " + product.getSerialNumber());
        contentStream.newLineAtOffset(50, 70);
        contentStream.showText("Description: " + product.getDescription());
        contentStream.newLineAtOffset(50, 80);
        contentStream.showText("Item condition: " + product.getItemCondition());

        contentStream.close();

        newDocument.save("product_detail.pdf");
        newDocument.close();
    }

    private Product loadProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found."));
    }
}
