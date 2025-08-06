A rudimentary e-commerce application. There is a list of products that can be viewed. Each user has his own cart when he registers, and can put items into their cart in different quantities and remove them. 
There are two different roles: a regular user and an admin. An admin can delete other users and upgrade users to the admin role. 

Docker instructions:
To build the docker image you must first run the maven clean and package goals. Then run the command from the directory of the project: docker build -t e-commerce-backend . (this dot at the end as well)
To run the docker container run the command: docker run -p 8080:8080 e-commerce-backend
