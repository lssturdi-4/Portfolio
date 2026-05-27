# Description
- The title of this project is JAT (Job Application Tracker). JAT provides a central location to track, manage, and update job applications for job seekers during their hiring journey.
- This project is fully developed in Java and GUI was built using JavaFX. Dependencies and external tools were utilize through Maven. 
- The program is running on JavaFX 21, Maven 3.1.3, and Java 21.0.11.

# How to Run
- The following versions for each tool are needed in order to run the program properly
    + Java 21.0.11
    + JavaFX 21
    + Maven 3.1.3
    + Latest version of VS Code

- Download the entire 'JAT' directory and open it with VS Code
- Migrate to App.java
    + The file's path is JAT/jat/jat/src/main/java/com/sturdy_softwares/App.java
- Click "Run" and the program will start
- User data is empty but can be stored once an entry is saved
    - Sample data can be deleted without any complications.

# Program Features
- Save an New Entry
    + Click '+' on the home page to add a new job entry
    + Fill each field with desired information (Pay amount must be numbers only)
    + If no errors are found, the entry will be saved and you will return to the home screen
        - Select cancel in order to hault the add entry transaction.
    + The new entry will be located on the bottom of the list on the homescreen
- Edit an Entry
    + With an entry selected on the home page, click 'edit' to open the edit form
    + The current information stored for the entry will be displayed and can be changed if desired
    + To discard all changes, press cancel. To save all changes, press save. Both actions will return to the home screen
- Open an Entry
    + When an entry is selected, the 'Open' button will open the entry and display its information.
    + No changes can be made on this page.
        - To make changes, click the edit button and you will be redirected to the edit page with the current entry's information

