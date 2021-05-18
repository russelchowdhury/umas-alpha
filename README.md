# umas alpha
A simple User Management and Authentication System

## Rest Endpoints of the Application is :

Server:

http://localhost:8801/

Auth Endpoints:

1) http://localhost:8801/auth/signup 
2) http://localhost:8801/auth/login

User Endpoint:

1) http://localhost:8801/api/users Get User List
2) http://localhost:8801/api/users/{id} Update with Request Body or Delete a User

h2 database:

http://localhost:8801/h2-console/

## Sample User:
```$xslt
{
    "username": "russelchy",
    "email": "russelchowdhury28@gmail.com",
    "password": "Abc!1234"
}
```

## Sample Request:
Register a User:
```$xslt
curl -H "Content-Type: application/json" -X POST http://localhost:8801/auth/signup -d "{\"username\":\"russelchy\",\"email\":\"russelchowdhury28@gmail.com\",\"password\":\"Abc!1234\"}"
```

Login User:
```$xslt
curl -H "Content-Type: application/json" -X POST http://localhost:8801/auth/login -d "{\"email\":\"russelchowdhury28@gmail.com\",\"password\":\"Abc!1234\"}"
```

List of Users:
```$xslt
curl -H "Content-Type: application/json" -X GET http://localhost:8801/api/users
```

Update a User:
```$xslt
curl -H "Content-Type: application/json" -X PUT http://localhost:8801/api/users/1 -d "{\"username\":\"russelchowdhury\",\"email\":\"russel.chowdhury@ucd.ie\",\"password\":\"Abc!1234\"}"
```

Delete a User:
```$xslt
curl -H "Content-Type: application/json" -X DELETE http://localhost:8801/api/users/1
```


## Future Work
* Add more Unit test
* Add Integration test
* Improve java docs
* Improve design
* Add Authorization
* Break Down To Microservices
* Containerization.


