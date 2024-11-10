# YESlingo
CSC207 final project

## Team members(github username):
Yucan Miao(heibaihaiermao),
Zhuxin Sun(juliexsun),
Ying Zhang(Zzoe00Y),
Jiner Zhang(jinerfreya),
Yuchen Zhao(emily-zhao-zz)

## Project Description

The Translator Application is a platform that provides users with translation capabilities across various mediums. It supports:
* **Text Translation**: Allows users to translate text into a specified language.
* **Image Translation**: Enables users to upload images, extract text, and translate it (example usages can be reading signs, menus, and documents).
* **Speech Translation**: Uses voice input to recognize and translate spoken language.

The application also includes:
* A **Phrasebook** for saving words or sentences based on user preference, designed for review and study purposes.
* Translation **History** to track and manage previous translations.
* A **Chatbot** feature for users to practice and study language in an interactive way.


## Entities

The primary entities within the application include:
* User: Base class for all users with basic profile information and preferred language.
* TranslationHistory: Represents a record of past translations, containing details such as source text and translated text.
* PhraseBookEntry: Represents an entry in the user's phrasebook, containing a saved phrase and its translation.
* ChatMessage: Represents messages exchanged in the chatbot feature.

## Use Cases

The project has been organized into distinct use cases that cover all core functionalities:

### User Management Use Cases

_Login:_ Handles user authentication.
_Logout:_ Manages user logout and session termination.
_Signup:_ Registers new users.
_ChangePassword:_ Allows users to update their passwords.

### Translation Use Cases

_ProcessTextTranslation:_ Handles the text translation feature.
_ProcessImageTranslation:_ Manages text extraction from images and subsequent translation.
_ProcessSpeechTranslation:_ Manages voice recognition and translation.

### Phrasebook and History Use Cases

_SaveToPhraseBook:_ Adds a word/sentence to the user's phrasebook.
_DeleteFromPhraseBook:_ Removes a word/sentence from the phrasebook.
_SaveTranslationHistory:_ Stores completed translations in the user's history.
_ClearTranslationHistory:_ Clears all entries from the translation history.
_DeleteSingleHistory_: Deletes individual entries from the translation history.

### Chatbot Use Case

ChatbotInteraction: Manages interactions between users and the chatbot, providing language practice and study support.
Additional Use Cases
CopyOutput: Enables users to copy translated text to the clipboard.
SwitchToHomepage: Manages navigation back to the homepage.

## Views

The application has a well-defined set of views that guide the user through various features:

_LoggedInView:_ Main dashboard for logged-in users.
_HistoryView:_ Shows saved translations in the user's history.
_PhrasebookView:_ Displays the user's saved phrases and translations.
_ChatbotView:_ Provides an interactive interface for the chatbot.
Navigation is handled by _ViewManager_, which manages transitions between views, providing a seamless user experience.

## Attributions

API:
1. Text to Text Translation: https://libretranslate.com/
2. Image to Text api key: https://app.nanonets.com/#keys 33a63559-9ed3-11ef-9412-8ac686cb80af
3. 

