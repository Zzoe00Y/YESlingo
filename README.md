# YESlingo
CSC207 final project

## Team members(github username):
Yucan Miao(heibaihaiermao),\
Zhuxin Sun(juliexsun),\
Ying Zhang(Zzoe00Y),\
Jiner Zhang(jinerfreya),\
Yuchen Zhao(emily-zhao-zz)

## Project Description

YESlingo is a translator application platform that provides users with translation capabilities across various mediums. It supports:
* **Text Translation**: Allows users to translate text into a specified language.
* **Image Translation**: Enables users to upload images, extract text, and translate them (such as reading signs, menus, and documents).
* **Speech Translation**: Uses voice input to recognize and translate spoken language.

The application also includes:
* Translation **History** to track and manage previous translations.
* A **Chatbot** feature for users to practice and study language interactively.


## Entities

The primary entities within the application include:
* User: Base class for all users with basic profile information and preferred language.\
* TranslationHistory: Represents a record of past translations, containing details such as source text and translated text.\
* ChatMessage: Represents messages exchanged in the chatbot feature.

## Use Cases

The project has been organized into distinct use cases that cover all core functionalities:

### User Management Use Cases

_Signup:_ Registers new users and creates new Users.\
_Login:_ Authenticates users.\
_CheckProfile:_ Allows users to view their profile information, including username, password, and preferred language.\
_ChangePassword:_ Enables users to update their passwords.\
_SetPreferredLanguage:_ Lets users set the default output language for translations.\
_Logout:_ Logs users out of their account.

### Translation Use Cases

_ProcessTextTranslation:_ Handles the text translation.\
_ProcessImageTranslation:_ Manages text extraction from images and translation.\
_ProcessSpeechTranslation:_ Manages voice recognition and translation.

### History Use Cases

_ClearTranslationHistory:_ Clears all entries from the translation history.\
_DeleteSingleHistory_: Deletes individual entries from the translation history.

### Chatbot Use Case

ChatbotInteraction: Manages user interactions and the chatbot, providing language practice and study support.\
Additional Use Cases\
CopyOutput: Enables users to copy the translated text to the clipboard.\
SwitchToHomepage: Manages navigation back to the homepage.\

## Views

The application has a well-defined set of views that guide the user through various features:

_SignUpView:_ Interface for new users to create an account.\
_LogInView:_ Interface for existing users to log into their accounts.\
_HomepageView:_ Main dashboard for logged-in users, offering various application features.\
_ProfileView:_ Displays user's profile information.\
_ChangePasswordView:_ Interface allowing users to change their passwords.\
_SetPreferredLanguageView:_ Interface allowing users to update their default translation output language.\
_HistoryView:_ Shows translation history.\
_ChatbotView:_ Provides an interactive interface for engaging with the chatbot.\
Navigation is handled by _ViewManager_, which manages transitions between views.

## Attributions

API:
1. Text to Text Translation: https://libretranslate.com/
2. Image to Text API key: https://app.nanonets.com/#keys 33a63559-9ed3-11ef-9412-8ac686cb80af
3. 

