
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
 After a successful, a login valid token is sent.|

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
| **Required**. To delete a user account authentication is required. |

#### Get item

```http
  PUT /users/{id}
```

| Description                       |
| :-------------------------------- |
| **Required**. The {id} must match the current user ID.
User must be authenticated. |




## Deployment

To run a docker database navigate to the root folder. Then use a command as follows.

```bash
  docker-compose up -d
```

Then you can start your spring boot application successfully.


## Running Tests

To run the tests, use the following command in the root file.

```bash
  mvn  test
```
Also, you can test the application's endpoints using Postman. Use the JSON schema below. **Make sure to add a Bearer token authorization in Postman. Use a token from login response.**

```
{
	"username": "yyy",
    "password": "xxx"
}
```
## Environment Variables

You can specify your own environment variables in application.properties. It is possible to change the JWT secret key, **important**
the key must meet Spring Security requirements by being sufficiently secure (256-bit key). You can also change the database password or username. Remember to change it also in the docker-compose.yml file.

