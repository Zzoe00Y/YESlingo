package external_services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Service for file translation.
 */
public class FileTranslationService {

    private static final String API_URL = "https://libretranslate.com/translate";
    private static final String API_KEY = "aafecfa0-294e-4518-a2d7-7e446e37690d";

    /**
     * Translates text from a `.txt` file using the LibreTranslate API.
     *
     * @param filePath      Path to the `.txt` file to be translated.
     * @param sourceLang    Source language code (e.g., "en").
     * @param targetLang    Target language code (e.g., "fr").
     * @return Translated text.
     * @throws IOException If there are errors during file reading or API call.
     */
    public String translate(String filePath, String sourceLang, String targetLang) throws IOException {
        // Ensure the file exists
        final File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File not found: " + filePath);
        }

        // Read the content of the `.txt` file
        final String fileContent = Files.readString(file.toPath());

        // Build the JSON payload for the LibreTranslate API
        final JSONObject jsonPayload = new JSONObject();
        jsonPayload.put("q", fileContent);
        jsonPayload.put("source", sourceLang);
        jsonPayload.put("target", targetLang);
        jsonPayload.put("api_key", API_KEY);

        // Create the request
        final OkHttpClient client = new OkHttpClient();
        final RequestBody body = RequestBody.create(
                jsonPayload.toString(),
                MediaType.parse("application/json")
        );

        final Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .build();

        // Execute the request
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("API Error: " + response.code() + " - " + response.body().string());
            }

            // Parse the response to get the translated text
            final String responseBody = response.body().string();
            final JSONObject jsonResponse = new JSONObject(responseBody);
            return jsonResponse.getString("translatedText");
        }
    }
}
