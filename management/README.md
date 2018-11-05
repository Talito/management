#To run the project:
1. Install mysql server
2. Create an admin with name 'root' and password 'root'
3. Create a database 'management'
4. Run the Spring Boot Application 'ManagementApplication'

## Test:
* You have a POSTMAN collection with examples of HTTP requests to the endpoints, you can import it to Postman and easily test the application.
* You have a few basic unit and integration tests. The integration tests runs a docker container with MySQL. Note that on the first run it will have to pull the MySQL docker image.

## Potential problems:
1. Docker daemon 'Permission denied'; you can fix it chmod 777 /var/run/docker.sock