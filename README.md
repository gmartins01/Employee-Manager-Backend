# Employee Manager Backend
Backend that allows to create jobs and employees as well as add a job to an employee.

## Installation
* Install [Docker Desktop](https://www.docker.com/products/docker-desktop/)
* Create the necessary Docker Images and Containers by running the following command in the project's root folder:
```
docker compose up --build -d
```
## Database
* Available at `localhost:5432/employee-manager`
  * **username**: root
  * **password**: root
  * **database**: employee

## Usage
It is recommended to use tools like Postman to interact with the API.

The API assumes the following:
 * All responses are made with `Content-Type: application/json`

### Authentication

First is necessary to register a user. It can be done using the endpoint 
`/auth/register`. For example,
to create an Admin:

```
POST /auth/register

{
    "login": "test@gmail.com",
    "password":"password",
    "firstName":"First",
    "lastName":"Last",
    "date_of_birth":"2001-02-03",
    "phone_number": "98363633",
    "ssn":111111,
    "nif":1211111,
    "address":"User address",
    "zip_code": "4205-544",
    "city":"City",
    "state":"State",
    "role":"ADMIN"
}
```

The,n it is possible to log in to get the access token:

```
POST /auth/login

{
    "login": "test@gmail.com",
    "password":"password"
}
```

### Jobs

Using the token, it is possible to access the rest of the endpoints. For example,
 to create a job:

```
POST /jobs

{
    "name": "Job name",
    "description": "Job description"
}
```

Listing all jobs can be done with a `GET /jobs`. 

To edit a job:

````
PUT /jobs

{
    "id":"jobId",
    "name":"New name",
    "description":"New description"
}
````
And finally, deleting a job can be done with `DELETE /jobs/{jobId}`, for example:
`DELETE /jobs/a8841bf9-f201-43f6-abc7-d851795146`


### Users

Listing all users can be done with a `GET /users`.

To edit a user:

```
PUT /users

{
    "login": "test1@gmail.com",
    "password":"Newpassword",
    "firstName":"First",
    "lastName":"Last",
    "date_of_birth":"2001-02-03",
    "phone_number": "98363633",
    "ssn":111111,
    "nif":1211111,
    "address":"User address",
    "zip_code": "4205-544",
    "city":"City",
    "state":"State",
    "role":"ADMIN"
}
```

To delete a user: `DELETE /users/{userId}`

To add a job to a user:

```
POST /users/job

{
    "userId":"userId",
    "jobId":"jobId"
}
```

And finally, to get the job of a user: `Get /users/{userId}/job`



## Technologies Used

- Java 11
- Spring Boot
- PostgreSQL
