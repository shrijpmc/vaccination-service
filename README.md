## Vaccination Service

### Description

Service that can keep track of the vaccination doses for the general population.

### Instructions to run the application

1. To run this application locally as a Docker container you need Docker Desktop installed on your machine.
[Docker Desktop] (https://docs.docker.com/desktop/install/mac-install/)


2. Download the image file from Docker Hub : [vaccination-service](https://hub.docker.com/repository/docker/shridevijpmc/vaccination-service/general).
3. Run below command.
   1. docker pull shridevijpmc/vaccination-service
   2. docker run -d --name companion-service -p 9091:9091
4. View and try out different end points by opening  http://localhost:9091/swagger-url.html URL.
