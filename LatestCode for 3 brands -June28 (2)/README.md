# CustomerCare UI Automation

This project has tests to verify customer care UI functionality. It is using Page Object Pattern and  written in Java using selenium.

## Getting Started

To get started install the required software and run the tests to verify.

### Prerequisites

Install the below prerequisites
- Java v1.8
- Maven
- Any IDE(Preferably IntelliJ)
- Allure(should be 2.10.0) : Download from [here](https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/2.10.0/allure-commandline-2.10.0.zip) and set [allure bin in the path](https://docs.qameta.io/allure/#_manual_installation)

## Running the tests

To run the tests use any of the two ways mentioned below.
- Running using maven
    - Open the project in terminal and run below command
      ```$ mvn clean test```
- Running testng.xml through IDE
    - Import the project to IntelliJIdea/any IDE, Build the project. Right click on testng.xml and select run.

## Project Folder Structure
### src
- src is the root folder which consists of Main folder and test folder
  #### main
    - Main has Java which consists of the java related files supporting the frame Work. It has Pages and utils folders.
      ##### Pages
        - Pages consists of the pages to support the framework. Pages contain objects and methods for a particular page.
      ##### Utils
        - This folder consists all the utilities required for the framework.
		
  #### test
    - This folder consists of tests, helper and listener classes.
    - Helper package consists of all email utils.

### Properties
    config.properties - This file consists of properties and test data.
    testsettings.properties - This file consists of email configurations.

## Run the Tests
- TwilioGmailTest
    - This class has both inbound and outbound email tests.

## Generate the Reports
Run the below commands to see the report.
```
allure generate target\allure-results --clean -o allure-report
allure open
```