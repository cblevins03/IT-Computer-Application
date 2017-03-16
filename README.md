# IT-Computer-Application
Application that stores information about a high school's computers in a database and allows users to access the data through a UI.

In order to run the application, you must download the H2 database for your machine, which can be downloaded here: http://www.h2database.com/html/download.html

Troubleshooting:

    Application will not run:
          Once the H2 database has dowloaded, search your machine for and run the H2 Console. It will appear in your internet browser.
          
          Copy the path in the JDBC URL field after "jdbc:h2:" (in my case it was "~/test").
          
          Locate the app.protperties which can be found at \BHSIT\src\main\resources.
          
          Make sure the db.path is the same as above ("~/test").
