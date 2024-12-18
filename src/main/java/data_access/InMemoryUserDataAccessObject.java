package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.Translation;
import entity.User;
import use_case.profile.change_language.ChangeLanguageUserDataAccessInterface;
import okhttp3.*;
import org.json.JSONObject;
import use_case.history.HistoryUserDataAccessInterface;
import use_case.profile.change_password.ChangePasswordUserDataAccessInterface;
import use_case.chatbot.ChatBotUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import view.ChangeLanguageView;
import use_case.text_translation.TextTranslationDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        ChangeLanguageUserDataAccessInterface,
        ChatBotUserDataAccessInterface,
        HistoryUserDataAccessInterface,
        TextTranslationDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    private String currentUsername;
    private String currentPassword;
    private String currentLanguage;

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public Translation translateText(String sourceText, String sourceLang, String targetLang) {
        final String API_URL = "https://api.mymemory.translated.net/get";
        final OkHttpClient client;

            client = new OkHttpClient.Builder()
                    .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                    .writeTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                    .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                    .build();

        try {
            System.out.println("Sending translation request to API: " + API_URL);

            // Build the URL with query parameters
            final HttpUrl.Builder urlBuilder = HttpUrl.parse(API_URL).newBuilder();
            urlBuilder.addQueryParameter("q", sourceText);
            urlBuilder.addQueryParameter("langpair", sourceLang + "|" + targetLang);

            final String url = urlBuilder.build().toString();
            System.out.println("Request URL: " + url);

            // Create request
            final Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            // Execute request
            final Response response = client.newCall(request).execute();
            final ResponseBody responseBody = response.body();
            final String responseString = responseBody != null ? responseBody.string() : "No response body";

            System.out.println("Response code: " + response.code());
            System.out.println("Response body: " + responseString);

            final JSONObject jsonResponse = new JSONObject(responseString);

            // Check for API errors in responseDetails
            final String responseDetails = jsonResponse.optString("responseDetails", "");
            if (!responseDetails.isEmpty()) {
                throw new RuntimeException(responseDetails);
            }

            final JSONObject responseData = jsonResponse.getJSONObject("responseData");
            final String translatedText = responseData.getString("translatedText");

            // Check if the translated text looks like an error message
            if (translatedText.toUpperCase().contains("INVALID")
                    ||
                    translatedText.toUpperCase().contains("ERROR")) {
                throw new RuntimeException("Translation failed: " + translatedText);
            }

            System.out.println("Successfully translated to: " + translatedText);

            return new Translation(sourceText, translatedText, sourceLang, targetLang);
        }
        catch (Exception e) {
            System.err.println("Translation error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Translation service error: " + e.getMessage());
        }
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public void changePassword(User user, String password) {
        this.currentPassword = password;
        get(user.getName()).setPassword(password);
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

    @Override
    public void changeOutputLanguage(String username, ChangeLanguageView.LanguageItem selectedLanguage) {
        this.currentLanguage = selectedLanguage.getDisplayName();
        get(username).setOutputLan(selectedLanguage.getDisplayName());
    }
}
