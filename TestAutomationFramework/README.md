# Framework
This Framework is built upon the foundation provided by the Academy's framework, 
enhancing its capabilities while preserving the core logic.

## api
This module provides comprehensive support methods and classes for API testing. 
The *Controller static classes contain template requests to the API.

### models
The models within this module are designed based on the request and response JSON bodies. 
They facilitate efficient serialization and deserialization operations.

## databasehelper
The databasehelper package comprises static classes that establish direct connections with the database. 
It is particularly useful for operations such as finding a user_id by username or deleting a user, 
tasks that may not be supported directly by the API endpoints. The main class, DatabaseHelper, 
serves as the primary interface, while other helper classes extend its functionality and add specific 
logic for different data tables. The DatabaseHelper classes are utilized in both UI and API testing contexts.

## factories
Utilizing the Factory Design Pattern, this package contains classes with static methods for generating data and objects. 
These factories are essential for quickly creating valid objects, a common requirement in testing scenarios.

## generations
The GenerateRandom class is equipped with static methods that generate arrays of random integers with a specified length. 
These integers are subsequently used to generate different characters. The methods in this class are primarily employed 
by the factories for data generation.

## models
The models package hosts representations of common objects frequently utilized in UI and API testing. 
These models are structured in alignment with the data schema of the application's objects. 
By utilizing these models, the testing process benefits from improved maintainability, readability, 
and ease of creation and validation.

## pages
The pages package employs the Page Object Model (POM) design, encapsulating the main logic behind the application's UI. 
These models feature private fields for locators, sourced from the ui_map.properties file. POMs offer improved maintainability, 
readability, and usability, promoting structured testing practices compared to traditional approaches 
involving ambiguous strings, numbers, and methods.

# test.testcases

## api
This section encompasses tests for the API, leveraging REST-assured for seamless execution. 
The tests are categorized based on the 5 REST controllers stipulated by the testing requirements, 
each named after the corresponding controllers in the application's source code. 
The BaseApiTest class serves as the primary foundation, providing common functionalities shared among the tests.

## UI
The UI module includes the BaseTest class, acting as the foundational class for all other test classes. 
It extends BaseApiTest, enabling the execution of functionality that may not be directly tested but can be interacted with 
via the API. For instance, when testing private sections of the application that require user authentication, 
the user is created through factories and registered via API. 
The cookie obtained from the logged-in status is then added to the WebDriver, facilitating the UI testing process.

### CreatePostTests
This section includes tests related to the creation of a post.

### CommentsTests
This segment encompasses tests focusing on the creation, editing, and deletion of comments associated with existing posts.

### PostTests
Here, tests are conducted to validate the editing and deletion functionalities of posts.

### RegisterTests
This section is dedicated to testing the registration form of the application.

## ui_map.properties
This file contains locators used within the Page Object Models (POMs), 
enhancing the reusability and maintainability of the UI testing infrastructure.

## config.properties
This configuration file stores critical information such as the URLs of the application under test 
and specific field limits (lower-bound and upper-bound). 
By centralizing this data, the project becomes more adaptable, 
enabling seamless adjustments without the need for intricate code alterations across numerous files.