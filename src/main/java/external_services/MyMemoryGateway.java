package external_services;

import entity.Translation;
import okhttp3.*;
import org.json.JSONObject;
import use_case.text_translation.TranslationGateway;

public class MyMemoryGateway implements TranslationGateway {
    private final String API_URL = "https://api.mymemory.translated.net/get";
    private final OkHttpClient client;

    public MyMemoryGateway() {
        client = new OkHttpClient.Builder()
                .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .writeTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .build();
    }

    @Override
    public Translation translateText(String sourceText, String sourceLang, String targetLang) {
        try {
            System.out.println("Sending translation request to API: " + API_URL);

            // Build the URL with query parameters
            HttpUrl.Builder urlBuilder = HttpUrl.parse(API_URL).newBuilder();
            urlBuilder.addQueryParameter("q", sourceText);
            urlBuilder.addQueryParameter("langpair", sourceLang + "|" + targetLang);

            String url = urlBuilder.build().toString();
            System.out.println("Request URL: " + url);

            // Create request
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            // Execute request
            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            String responseString = responseBody != null ? responseBody.string() : "No response body";

            System.out.println("Response code: " + response.code());
            System.out.println("Response body: " + responseString);

            JSONObject jsonResponse = new JSONObject(responseString);

            // Check for API errors in responseDetails
            String responseDetails = jsonResponse.optString("responseDetails", "");
            if (!responseDetails.isEmpty()) {
                throw new RuntimeException(responseDetails);
            }

            JSONObject responseData = jsonResponse.getJSONObject("responseData");
            String translatedText = responseData.getString("translatedText");

            // Check if the translated text looks like an error message
            if (translatedText.toUpperCase().contains("INVALID") ||
                    translatedText.toUpperCase().contains("ERROR")) {
                throw new RuntimeException("Translation failed: " + translatedText);
            }

            System.out.println("Successfully translated to: " + translatedText);

            return new Translation(sourceText, translatedText, sourceLang, targetLang);

        } catch (Exception e) {
            System.err.println("Translation error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Translation service error: " + e.getMessage());
        }
    }
}