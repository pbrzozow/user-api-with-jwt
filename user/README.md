
# Simple CRUD API with Spring Boot and JWT

API allows users to create accounts, log
in, view a list of users, updating their profiles, and delete users by their ID. Viewing, updating and deleting users is only allowed for authenticated users. Also password encryption is provided.

## API Reference

#### Get all items

```http
  POST /register
```

| Description                |
| :------------------------- |
| Endpoint registers new user. |

#### Get item

```http
  POST /login
```

| Description                       |
| :-------------------------------- |
| **Required**. Username and password.
 After successful login valid token is being sent.|

#### Get item

```http
  GET /users
```

| Description                                                  |
|:-------------------------------------------------------------|
| **Required**. User must be logged in to receive a user list. |

#### Get item

```http
  DELETE /users/{id}:
```

| Description                       |
| :-------------------------------- |
| **Required**. To delete user account authentication is required. |

#### Get item

```http
  PUT /users/{id}
```

| Description                       |
| :-------------------------------- |
| **Required**. {id} must be equal to current user id.
User must be authenticated. |




## Deployment

To run a docker database navigate to the root folder. Then use a command as follows.

```bash
  docker-compose up -d
```

Then you can start your spring boot application successfully.


## Running Tests

To run tests, run the following command in the root file.

```bash
  mvn  test
```
Also using Postman you can test application endpoints.
Use a json schema from below. **Make sure to add Bearer token authorization in Postman. Use a token from login response.**

```
{
	"username": "yyy",
    "password": "xxx"
}
```
## Environment Variables

You can specify your own environment variables in application.properties. It is possible to change JWT secret key **important**
key must be enough secure for spring security requirements (256-bit key). You can also change database password or username. Remember to change it also in docker-compose.yml file.

