Project Description
The Translator Application is a versatile platform that provides users with translation capabilities across various mediums. It supports:
Text Translation: Allows users to translate typed or pasted text into a specified language.
Image Translation: Enables users to upload images, extract text through OCR, and translate it (ideal for reading signs, menus, and documents).
Speech Translation: Uses voice input to recognize and translate spoken language.
The application also includes:
A Phrasebook for saving words or sentences based on user preference, designed for study and reference purposes.
Translation History to track and manage previous translations.
A Chatbot feature for users to practice and study language in an interactive way.

Features
Core Features
Text Translation: Supports translation of user-input text across various languages.
Image Translation: Extracts text from the image and translates to the ideal language.
Speech Translation: Recognizes voice input, converts it to text, and translates it to the user's chosen language.
Additional Features
Phrasebook: Users can save important translations in a personalized phrasebook for later review or study purposes.
Translation History: Stores past translations, allowing users to manage and revisit previous translations.
Chatbot: Engages users in conversational practice, helping them learn and study languages interactively.

Entities
The primary entities within the application include:
User: Base class for all users with basic profile information and preferences.
TranslationHistory: Represents a record of past translations, containing details such as source text and translated text.
PhraseBookEntry: Represents an entry in the user's phrasebook, containing a saved phrase and its translation.
ChatMessage: Represents individual messages exchanged in the chatbot feature.

Use Cases
The project has been organized into distinct use cases that cover all core functionalities:
Translation Use Cases
ProcessTextTranslation: Handles the text translation feature.
ProcessImageTranslation: Manages text extraction from images and subsequent translation.
ProcessSpeechTranslation: Manages voice recognition and translation.
User Management Use Cases
Login: Handles user authentication.
Logout: Manages user logout and session termination.
Signup: Registers new users.
ChangePassword: Allows users to update their passwords.
Phrasebook and History Use Cases
SaveToPhraseBook: Adds a translation to the user's phrasebook.
DeleteFromPhraseBook: Removes a translation from the phrasebook.
SaveTranslationHistory: Stores completed translations in the user's history.
ClearTranslationHistory: Clears all entries from the translation history.
DeleteSingleHistory: Deletes individual entries from the translation history.
Chatbot Use Case
ChatbotInteraction: Manages interactions between users and the chatbot, providing language practice and study support.
Additional Use Cases
CopyOutput: Enables users to copy translated text to the clipboard.
SwitchToHomepage: Manages navigation back to the homepage.

Views
The application has a well-defined set of views that guide the user through various features:
Authentication Views: LoginView, SignupView
Main Application Views:
LoggedInView: Main dashboard for logged-in users.
HistoryView: Shows saved translations in the user's history.
PhrasebookView: Displays the user's saved phrases and translations.
ChatbotView: Provides an interactive interface for the chatbot.
Navigation is handled by ViewManager, which manages transitions between views, providing a seamless user experience.

Attributions
API:
Text to Text Translation: https://libretranslate.com/
Image to Text api key: https://app.nanonets.com/#keys 33a63559-9ed3-11ef-9412-8ac686cb80af


