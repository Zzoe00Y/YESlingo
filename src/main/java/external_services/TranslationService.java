package external_services;

public interface TranslationService {
    /**
     * Translates the given text to the specified target language.
     *
     * @param text the text to translate
     * @param targetLanguage the language to translate the text into
     * @return the translated text
     * @throws Exception if an error occurs during translation
     */
    String translate(String text, String targetLanguage) throws Exception;
}
