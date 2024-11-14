package external_services;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.io.InputStream;

public class NanonetsImageToTextService implements ImageToTextService {

    private static final String API_KEY = "33a63559-9ed3-11ef-9412-8ac686cb80af";
    private static final String API_URL = "https://app.nanonets.com/v2/OCR/Model/<MODEL_ID>/LabelFile/";

    @Override
    public String extractText(BufferedImage image) throws Exception {
        // Convert BufferedImage to Base64 encoded string
        String base64Image = bufferedImageToBase64(image);

        // Create JSON payload with the encoded image
        JSONObject jsonPayload = new JSONObject();
        jsonPayload.put("file", "data:image/png;base64," + base64Image);

        // Set up HTTP connection
        URL url = new URL(API_URL.replace("<MODEL_ID>", "your_model_id")); // Replace with actual model ID
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString((API_KEY + ":").getBytes()));
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Send JSON payload
        try (OutputStream os = conn.getOutputStream()) {
            os.write(jsonPayload.toString().getBytes());
            os.flush();
        }

        // Read the response
        StringBuilder response = new StringBuilder();
        try (InputStream is = conn.getInputStream()) {
            int ch;
            while ((ch = is.read()) != -1) {
                response.append((char) ch);
            }
        }

        // Parse JSON response to get the extracted text
        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONArray results = jsonResponse.getJSONArray("result");
        StringBuilder extractedText = new StringBuilder();
        for (int i = 0; i < results.length(); i++) {
            JSONArray predictions = results.getJSONObject(i).getJSONArray("prediction");
            for (int j = 0; j < predictions.length(); j++) {
                extractedText.append(predictions.getJSONObject(j).getString("ocr_text")).append(" ");
            }
        }

        return extractedText.toString().trim();
    }

    private String bufferedImageToBase64(BufferedImage image) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        byte[] imageBytes = outputStream.toByteArray();
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}
