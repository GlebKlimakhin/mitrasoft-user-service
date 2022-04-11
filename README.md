# mitrasoft-user-service
User-service with CRUD-operations support:
Avalible endpoints:

1) GET https://user-service-mitrasoft.herokuapp.com/api/v1/users/ - for Users list (ROLE_ADMIN required)
2) GET https://user-service-mitrasoft.herokuapp.com/api/v1/users/{id} - for User by ID (No role required)
3) POST https://user-service-mitrasoft.herokuapp.com/api/v1/users/  - for creating Users(ROLE ADMIN requred) (request dto - in project)
4) PUT https://user-service-mitrasoft.herokuapp.com/api/v1/users/roles/ - for updating users' roles (request dto - in project)
5) PUT https://user-service-mitrasoft.herokuapp.com/api/v1/users/info/ - for updating users' information (username, password, id required)
6) DELETE https://user-service-mitrasoft.herokuapp.com/api/v1/users/{id} - for deleting users by IDs

FOR AUTH:

POST https://user-service-mitrasoft.herokuapp.com/api/v1/oauth/token Credentials required(login, password)

given oauth token, we can add "Authorization" header for accessing resouce annotated @PreAuthorized

migration in a "recource/db.migration" folder

for local deploying we need to download tomcat, build a jar-file and to use tomcat(servlet container) to deploy the app
