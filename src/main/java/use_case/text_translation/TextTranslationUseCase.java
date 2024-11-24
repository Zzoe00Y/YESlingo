package use_case.text_translation;

import external_services.TranslationService;

public class TextTranslationUseCase {
    private final TranslationService translationService;

    public TextTranslationUseCase(TranslationService translationService) {
        this.translationService = translationService;
    }

    public String translate(String text, String targetLanguage) throws Exception {
        return translationService.translate(text, targetLanguage);
    }
}