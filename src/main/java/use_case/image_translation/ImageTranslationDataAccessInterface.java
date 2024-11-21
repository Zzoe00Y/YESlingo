package use_case.image_translation;
import entity.User;

import java.awt.image.BufferedImage;
import java.util.List;

public abstract class ImageTranslationDataAccessInterface {
    void imageTranslation(User user, BufferedImage image) {}

    public abstract void saveTranslation(String imageId, String originalText, String translatedText);

    public abstract List<String> getTranslationHistory();

    public abstract void deleteTranslation(String imageId);
}
