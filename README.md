# FIIMDb

- copy the content from FIIMDb/lib folder to tomcat/lib folder
- the project uses mysql database
- you can import the java project using the WAR file and the database
   using sql file
- default credentials for admin accound: username - admin, password - admin
- you can update admin's infos except the username
- a trigger was created in database: when an user is inserted in
   database, the trigger will insert in the user_roles table the 
   new user and the "user" role to it
- when a new user is registered, a check is made to avoid duplicate
  usernames and emails
- error message is printed on "register.jsp" page when an username or 
  email already exists

