# Thesis BE app

# Start mariadb with docker

Apply proper configuration in `docker-compose.yaml` file, so that we can use those defined values
to config the `application.properties` file for the spring-boot application
* define technical username/password
* define db name

In current directory, run

    docker-compose up -d

After application start, we can login to mysql by terminal and check the result

    docker container ls

    # copy container-id
    
    docker exec -it <container-id> /bin/bash

    # login mysql with root acount

    mysql -u root -p root

    show databases;

    use <database-name>;

    show tables;

# config spring-boot application

Open  `application.properties` and set value for
* db url contain domain + port + schema name;
* db username/password that were defined in docker-compose file