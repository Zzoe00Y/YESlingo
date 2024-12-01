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
1. As a user, I want to log into the system with my username and password so that I can access personalized translation settings.
2. As a user, I want to translate text into a different language so that I can understand content written in a language I don’t speak.
3. As a user, I want to upload a .txt file and have its content translated into my desired language so that I can easily understand text in languages I am unfamiliar with.
4. As a user, I want to be able to speak English and have my speech recognized as text, then translated into my desired different language.
5. As a user, I want my text translations to be automatically saved to history, and the history can be cleared if I want to.
6. As a user, I want to simulate real time conversation with a chatbot in specified input and output languages.

## Installation Instructions & Usage

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/Zzoe00Y/YESlingo.git
2. Run the main file: Open your IDE or terminal, navigate to the main file, and run it to start your translation journey!

### Usage
1. **Select your source and target language:**  
   Use the language selection bar in the UI to choose the languages for translation. For example, select English as the source language and French as the target language.

2. **Choose your desired translation method:**
    - **Text translation:** Type text directly into the input field to translate.
    - **File translation:** Prepare a `.txt` file with the text to be translated and upload it using the file upload button.
    - **Voice translation:** Enable your microphone and start speaking to have your voice detected and translated into the target language.
    - **Chatbox:** Click on the chatbox feature to interact with the system in real time. The chatbox will simulate a conversation in specified input and output languages.
    - **Translation history:** Log in with your username to access your translation history. The history section allows you to view and reuse your previously translated content.


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
