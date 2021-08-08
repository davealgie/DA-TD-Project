# ToDo Project
This project is a Full stack Web application which acts as a system for creating and manipulating a To Do List or short for TDL, which will allow us to add, edit, read and delete to dos. The application was created using Spring Boot and It has been tested using Unit, Integration and Selenium tests.

# Getting Started
In order to run and test the application you firstly require Java(v1.8 onward), MySql, Eclipse(recommended), Visual Studio Code(reccommended), Spring Boot and Maven installed your personal computer. Links for downloading them are provided on the Built With section down below.

# Prerequisites
In order to get our application setup, we need to follow the below instructions.

- Clone this repository to your local machine.

- Open the backend folder with Eclipse IDE.

- After the project is loaded, you should be able to see it on the Package Explorer window placed on the left hand side.

- Right-click the project folder and click Run As > Spring Boot App

- The Spring Boot App will start the server on port 8080.

- After that, It is time to open the front-end part of our application and run it on a browser. To do so, open the project folder from visual studio code. Go to index.html, right click from the document and select Open With Live server(This takes into account, your visual studio code has the extension of live server installed).

- The application will open from the browser and we are ready to play with it. You will be presented with a Homepage and a To Do list page.

# Testing


# Technologies
- [Maven](https://maven.apache.org/) - Build Tool and Dependency Management
- [Spring Boot](https://spring.io/projects/spring-boot) - API Development Platform
- [Java](https://www.java.com/en/) - Back-End Programming Language
- [MySql](https://www.mysql.com/) - Database
- [Eclipse](https://www.eclipse.org/) - IDE
- Visual Studio Code - Code Editor
- Git - Version Control
- Jira - Software helping managing the work
- [HTML5, CSS, JS] - Front-End Web Technologies

# Author
- David Algie

# Acknowledgments
- Thank you trainers who have helped me throughout this project.
- Thank you google for being the fixers of my errors!
- Shout out to George Whyte for listening to me complain while attempting to fix my issues.

# Running the tests
The tests have both Integration and Unit tests, to run the integration you right click in tests on the file ToDoTest and run as a Junit, while for the unit tests, do the same for the ToDoUnitTest. JUnit and Integration tests surpass the industry standard requirment of 80%.

# Selenium Tests:
A chrome web driver was added to the files of the project so that Selenium tests can be run. Going to a Selenium test and right clicking Run as a Junit Test will run it for you. They test the functionality of the website, So the CRUD functions and asserting to test whether they we're successful.
