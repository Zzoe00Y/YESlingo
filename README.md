# YESlingo
Group 177's CSC207 Final Project

## Team members(github username):
Yucan Miao(heibaihaiermao)\
Zhu Xin Sun(juliexsun)\
Ying Zhang(Zzoe00Y)\
Jiner Zhang(jinerfreya)\
Yuchen Zhao(emily-zhao-zz)

## Project Description

YESlingo is a translator application platform that provides users with translation capabilities across various mediums. It supports:
* **Text Translation**: Allows users to translate text into a specified language.
* **File Translation**: Enables users to upload files, extract text, and translate them.
* **Speech Translation**: Uses voice input to recognize and translate spoken language.

The application also includes:
* Translation **History** to track and manage previous translations.
* A **Chatbot** feature for users to practice and study language interactively.

## User Stories
TODO

## Installation instructions & Usage
TODO

## Entities

The primary entities within the application include:
* User: Base class for all users with basic profile information and preferred language.
* Translation: Represents a translation, containing source text, translated text, source language, and translated language.
* ChatMessage: Represents messages exchanged in the chatbot feature.

## Use Cases

The project has been organized into distinct use cases that cover all core functionalities:

### User Management Use Cases

_Signup:_ Registers new users and creates new Users.\
_Login:_ Authenticates users.\
_CheckProfile:_ Allows users to view their profile information, including username, password, and preferred language.\
_ChangePassword:_ Enables users to update their passwords.\
_ChangePreferredLanguage:_ Lets users change the default output language for translations.\
_Logout:_ Logs users out of their account.

### Translation Use Cases

_ProcessTextTranslation:_ Handles the text translation.\
_ProcessFileTranslation:_ Manages text extraction from files and translation.\
_ProcessSpeechTranslation:_ Manages voice recognition and translation.

### History Use Cases

_ClearTranslationHistory:_ Clears all entries from the translation history.\

### Chatbot Use Case

_sentChat:_ Generates and displays a response to the user's input when the user clicks the sent button \
_pullUser:_ Pull user information from DAO and update the chatBotView's state\

## Views

The application has a well-defined set of views that guide the user through various features:

_SignUpView:_ Interface for new users to create an account.\
_LogInView:_ Interface for existing users to log into their accounts.\
_HomepageView:_ Main dashboard for logged-in users, offering various application features.\
_ProfileView:_ Displays user's profile information.\
_ChangePasswordView:_ Interface allowing users to change their passwords.\
_ChangeLanguageView:_ Interface allowing users to update their default translation output language.\
_HistoryView:_ Shows translation history.\
_ChatbotView:_ Provides an interactive interface for engaging with the chatbot.\
Navigation is handled by _ViewManager_, which manages transitions between views.

## Attributions

API:
1. Text to Text Translation: [https://libretranslate.com/](https://mymemory.translated.net/)
2. File to Text API key: https://libretranslate.com/translate
3. ChatBot Cohere API: https://docs.cohere.com/v1/reference/chat O40OXvNOKzdUtm6vQlpLiE7erjfv81ZeFUeHbvmg
4. Speech to Text API - Sphinx4: https://cmusphinx.github.io/doc/sphinx4/javadoc/


## License
TODO

## Feedback?
