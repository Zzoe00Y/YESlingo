package external_services;

import java.awt.image.BufferedImage;

public interface ImageToTextService {
    /**
     * Extracts text from the provided image using OCR.
     *
     * @param image the image to process
     * @return the extracted text from the image
     * @throws Exception if an error occurs during OCR processing
     */
    String extractText(BufferedImage image) throws Exception;
}
