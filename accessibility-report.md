# Accessibility Report
### Principle 1: Equitable Use
- **Numerous Available Input and Output Languages**  
  By allowing users change their preferred language, the program accommodates speakers of diverse languages, ensuring accessibility for non-English users.
- **Speech-to-Text & File Upload Feature:**  
  This feature supports users with disabilities that make typing challenging, motor impairments or a visual disability, making the program accessbile to a larger group.
- **Future Implementation**  
  The UI can be updated to be in more langauges for even more accessible use for non-English speakers.

### Principle 2: Flexibility in Use
- **Multiple Input Methods:**  
  Users can choose between text translation, speech-to-text, or file uploads, catering to different preferences and accessibility needs.
- **Customizable User Profiles:**  
  Allowing users to adjust their preferred language and other settings provides a personalized experience that adapts to individual needs and preferences.
- **Future Implementations**
  Enable adjustable font sizes, themes, and color schemes to cater to users with varying visual needs

### Principle 3: Simple and Intuitive Use
- **User-Friendly Interface:**  
  The straightforward sign-up, login, and translation done through just clicks make the program easy to use for individuals regardless of their technical expertise.
  The button labels for other functions like voice input and file upload are clear and concise to guide the user through the program.
- **Future Implementations**  
  The program needs to be run using a compiler in an IDE which may be difficult for people not familiar with coding. In the future, 
  we can extend the UI to for example HTML for easier access.


### Principle 4: Perceptible Information
- **Visual Feedback**  
  Provides textual output only, but in numerous languages so the user can access the information regardless of their preferred language.
- **Future Implementations**  
  The ability for the program to convert text-to-speech output for accomodating the visually impared as there were no readily available API's to complete this task during the project.

### Principle 5: Tolerance for Error
- **No Physical Harm**  
  There are no obviously physically harmful features such as continuous flashes.
- **Password Change and Validation:**  
  There are checks that force the user to enter their password twice to make sure it is intended.
  This allows the user to change their password in the case that they accidentally entered a wrong one.
- **File Upload, Input and Output Language, and Voice Input Validations**  
  Ensuring that only .txt files can be uploaded for translation reduces the chances of errors and enhances the reliability of the translation process.
  and making sure the user is translating to a different language to minimize physical exertion of retyping information, and
  making sure 

### Principle 6: Low Physical Effort
- **Speech-to-Text Capability**  
  Reduces the need for extensive typing, making the program accessible and comfortable for users who may experience fatigue or have limited dexterity.
- **Efficient Navigation**  
  One-click button menu and shortcuts allow users to perform tasks quickly without unnecessary physical actions, minimizing fatigue during extended use.

### Principle 7: Size and Space for Approach and Use
- **JavaSwing UI**  
  Ensures that the program is accessible on various devices and screen sizes, providing adequate space for interaction regardless of the user's device or physical environment.
- **Accessible Navigation Elements**  
  Buttons and interactive elements are designed with appropriate size to be visible for people who do not have significant visual impairments.
- **Future Implementations**
  Implement more customizable screen layouts and interactions (like eye-tracking) for users who may have specific accessibility needs, such as individuals with limited motor skills.

---

### Target Market
We would market this translation program primarily towards international students and professionals who frequently engage in multilingual communication. Additionally, it would appeal to businesses operating in global markets that require efficient translation services for documents and communication. Educational institutions could also benefit from licensing the program to support their diverse student bodies, enhancing language accessibility and learning experiences. Overall, although not as equitable, marketing the program toward these groups will cover the most demographics who can use our program without issue, as people discussed in the section below are the minority.
### Demographic Usage Considerations
While the program is designed to be accessible to the public in need of translation, certain demographics may face barriers to usage. For instance, individuals with limited access to modern devices might need help to utilize features like speech-to-text and file uploads effectively. Additionally, older adults less familiar with digital interfaces may require additional support since the program must be run and compiled using an IDE. Even more, visually impaired people will not be able to navigate the program at all since its UI relies on clicking on the views. For a more equitable program in the future, we can implement a text-to-speech feature for the translation output to accommodate the visually impaired or dyslexic crowd, or move the UI to, for instance, an HTML website to accommodate the population unfamiliar with IDEs.
