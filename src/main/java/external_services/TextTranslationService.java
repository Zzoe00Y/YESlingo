package external_services;

import entity.Translation;
import entity.User;
import okhttp3.*;
import org.json.JSONObject;

import use_case.text_translation.TextTranslationDataAccessInterface;

/**
 * Service that handles text translation operations and user translation history.
 */
public class TextTranslationService implements TextTranslationDataAccessInterface {
    private static final String API_URL = "https://api.mymemory.translated.net/get";
    private final OkHttpClient client;

    public TextTranslationService() {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .writeTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .build();
    }

    @Override
    public Translation translateText(String sourceText, String sourceLang, String targetLang) {
        try {
            final HttpUrl.Builder urlBuilder = HttpUrl.parse(API_URL).newBuilder();
            urlBuilder.addQueryParameter("q", sourceText);
            urlBuilder.addQueryParameter("langpair", sourceLang + "|" + targetLang);

            final Request request = new Request.Builder()
                    .url(urlBuilder.build())
                    .get()
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful() || response.body() == null) {
                    throw new RuntimeException("API call failed: " + response.code());
                }

                final JSONObject jsonResponse = new JSONObject(response.body().string());
                validateResponse(jsonResponse);

                final String translatedText = jsonResponse.getJSONObject("responseData")
                        .getString("translatedText");

                return new Translation(sourceText, translatedText, sourceLang, targetLang);
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Translation service error: " + e.getMessage());
        }
    }

    @Override
    public void save(User user) {
        // Implement saving user's translation history
    }

    @Override
    public User get(String username) {
        // Implement retrieving user with translation history
        return null;
    }

    private void validateResponse(JSONObject jsonResponse) {
        final String responseDetails = jsonResponse.optString("responseDetails", "");
        if (!responseDetails.isEmpty()) {
            throw new RuntimeException(responseDetails);
        }

        final String translatedText = jsonResponse.getJSONObject("responseData")
                .getString("translatedText");
        if (translatedText.toUpperCase().contains("INVALID")
                ||
                translatedText.toUpperCase().contains("ERROR")) {
            throw new RuntimeException("Translation failed: " + translatedText);
        }
    }
}