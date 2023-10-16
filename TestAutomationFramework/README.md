# Framework
This Framework is using the framework, the Academy kindly provided, as a base.
There are a lot of things added, but the core logic is still intact.
## api
Provides support methods and classes for API testing. The *Controller static classes
contain template requests to te API.
### models
Objects based on the request json bodies/response json bodies. They are used
for serialization/deserialization.
## databasehelper
Static classes through which we connect with the database directly. It is sometimes
needed, for example when we want to find user_id by username or when we want to
delete a user (there is no API endpoint for DELETE user).
The main class is DatabaseHelper.
The other helpers call DatabaseHelper's methods, and may add additional logic
needed for the certain data table.
DatabaseHelper-s are used both from the UI tests and from the API tests.
## factories
Factory Design Pattern, these are classes containing static methods for generating
data and objects. More often than not, in tests, we need a quick creation of valid
objects. The factories do that for us.
## generations
GenerateRandom class contains static methods that generate array with set length
that contains random integers. Based on these integers, it gets different characters.
Primarily the factories use this class's methods.
## models
Contains models for common objects we create during UI and API testing.
They are based on what data the certain objects in the application have.
For example, a user has: username, email, password, profile, etc.
The purpose of having models for these things is:
1. Greater maintainability
2. Greater readability
3. Easier creation
4. Easier validation
## pages
Page Object Models, that contain the main logic behind UI of the application.
They have private fields for the locators, which they get from the ui_map.properties file.
POMs have greater maintainability, readability, and usability compared to magic
strings, magic numbers, and magic methods.

# test.testcases
## api
Tests for the API, using RESTassured. They are grouped by the 5 REST controllers
the requirements state must be tested. They are named by the corresponding controllers
in the source code of the application.
There is a main BaseApiTest class, that holds some common logic, and the other classes
extend BaseApiTest class.
## UI
There is a main class that is extended by every other class - BaseTest.
It extends BaseApiTest, because whenever we don't test a certain functionality,
but we need to use it, through API it will be much faster than through the UI.
For example, whenever we do UI testing in the private part of the application, which
needs authorization as a user, the user is created through factories and registered through API.
Through API request we get the cookie for the logged-in status, and we add it to the WebDriver.
### CreatePostTests
Contains tests about the creation of a post.
### CommentsTests
Contains tests about the creation, editing, and deleting of comments under an already created
Post.
### PostTests
Contains tests about the editing and deleting of a post.
### RegisterTests
Contains tests about the registration form.
## ui_map.properties
Contains locators used in POMs
## config.properties
Contains the URL-s of the application under test, and common information such as
limits of every field - lower-bound and upper-bound. This data could change in time,
so it is better to have it in a config file than to have it hard-coded in myriad of
files.