# Library API
RESTFul API for Library CRUD application

# Authentication Endpoints

## Login
**Method:** POST<br>
**URI:** /auth/login<br>
**Request body:**<br>
```
{
    "email": "<EMAIL>",
    "password": "<PASSWORD>"
}
```
**Response body (success):**<br>
```
{
    "token": "<TOKEN>"
}
```

## Register
**Method:** POST<br>
**URI:** /auth/register<br>
**Request body:**<br>
```
{
    "email": "<EMAIL>",
    "password": "<PASSWORD>"
}
```
**Response body (success):**<br>
```
{
    "message": "SUCCESS"
}
```

# Library Endpoints

## List categories
**Method:** GET<br>
**URI:** /library/public/categories<br>
**Response body (success):**<br>
```
[
    {
        "id": <ID>,
        "name": <NAME>
    },
    {
        "id": <ID>,
        "name": <NAME>
    },
    {
        "id": <ID>,
        "name": <NAME>
    },
]
```

## List bookEntities
**Method:** GET<br>
**URI:** /library/public/bookEntities<br>
**Response body (success):**<br>
```
[
    {
        "id": <BOOK_ID>,
        "name": <BOOK_NAME>,
        "author": <BOOK_AUTHOR>,
        "publishDate": <BOOK_PUBLISH_DATE>,
        "categoryEntity": {
            "id": <CATEGORY_ID>,
            "name": <CATEGORY_NAME>
        },
        "pickerEmail": <PICKER_USER_EMAIL>,
        "available": true
    },
    {
        "id": <BOOK_ID>,
        "name": <BOOK_NAME>,
        "author": <BOOK_AUTHOR>,
        "publishDate": <BOOK_PUBLISH_DATE>,
        "categoryEntity": {
            "id": <CATEGORY_ID>,
            "name": <CATEGORY_NAME>
        },
        "pickerEmail": <PICKER_USER_EMAIL>,
        "available": true
    }
]
```

## Search bookEntities
**Method:** GET<br>
**URI:** /library/public/bookEntities?name=<NAME>&categoryId=<CATEGORY_ID>&author=<AUTHOR>&searchAndOr=OR<br>
**Response body (success):**<br>
```
[
    {
        "id": <BOOK_ID>,
        "name": <BOOK_NAME>,
        "author": <BOOK_AUTHOR>,
        "publishDate": <BOOK_PUBLISH_DATE>,
        "categoryEntity": {
            "id": <CATEGORY_ID>,
            "name": <CATEGORY_NAME>
        },
        "pickerEmail": <PICKER_USER_EMAIL>,
        "available": true
    },
    {
        "id": <BOOK_ID>,
        "name": <BOOK_NAME>,
        "author": <BOOK_AUTHOR>,
        "publishDate": <BOOK_PUBLISH_DATE>,
        "categoryEntity": {
            "id": <CATEGORY_ID>,
            "name": <CATEGORY_NAME>
        },
        "pickerEmail": <PICKER_USER_EMAIL>,
        "available": true
    }
]
```

## Pick up bookEntity
**Method:** POST<br>
**URI:** /library/private/pickupEntity<br>
**Request header:**<br>
```
Authorization: <TOKEN>
```
**Request body:**<br>
```
{
    "bookId": <BOOK_ID>
}
```
**Response body (success):**<br>
```
{
    "id": <PICKUP_ID>,
    "dropOff": false,
    "bookEntity": {
        "id": <BOOK_ID>,
        "name": <BOOK_NAME>,
        "author": <BOOK_AUTHOR>,
        "publishDate": <BOOK_PUBLISH_DATE>,
        "categoryEntity": {
            "id": <CATEGORY_ID>,
            "name": <CATEGORY_NAME>
        },
        "pickerEmail": <PICKER_USER_EMAIL>,
        "available": true
    },
    "createdAt": <TIMESTAMP>,
    "updatedAt": <TIMESTAMP>
}
```

## Drop off bookEntity
**Method:** POST<br>
**URI:** /library/private/dropoff<br>
**Request header:**<br>
```
Authorization: <TOKEN>
```
**Request body:**<br>
```
{
    "bookId": <BOOK_ID>
}
```
**Response body (success):**<br>
```
{
    "id": <PICKUP_ID>,
    "dropOff": true,
    "bookEntity": {
        "id": <BOOK_ID>,
        "name": <BOOK_NAME>,
        "author": <BOOK_AUTHOR>,
        "publishDate": <BOOK_PUBLISH_DATE>,
        "categoryEntity": {
            "id": <CATEGORY_ID>,
            "name": <CATEGORY_NAME>
        },
        "pickerEmail": <PICKER_USER_EMAIL>,
        "available": true
    },
    "createdAt": <TIMESTAMP>,
    "updatedAt": <TIMESTAMP>
}
```

## List currently picked up bookEntities
**Method:** GET<br>
**URI:** /library/private/currentPickups<br>
**Request header:**<br>
```
Authorization: <TOKEN>
```
**Response body (success):**<br>
```
[
    {
        "id": <PICKUP_ID>,
        "dropOff": true,
        "bookEntity": {
            "id": <BOOK_ID>,
            "name": <BOOK_NAME>,
            "author": <BOOK_AUTHOR>,
            "publishDate": <BOOK_PUBLISH_DATE>,
            "categoryEntity": {
                "id": <CATEGORY_ID>,
                "name": <CATEGORY_NAME>
            },
            "pickerEmail": <PICKER_USER_EMAIL>,
            "available": true
        },
        "createdAt": <TIMESTAMP>,
        "updatedAt": <TIMESTAMP>
    }
]
```

## List history of picked up/dropped off bookEntities
**Method:** GET<br>
**URI:** /library/private/history<br>
**Request header:**<br>
```
Authorization: <TOKEN>
```
**Response body (success):**<br>
```
[
    {
        "id": <PICKUP_ID>,
        "dropOff": true,
        "bookEntity": {
            "id": <BOOK_ID>,
            "name": <BOOK_NAME>,
            "author": <BOOK_AUTHOR>,
            "publishDate": <BOOK_PUBLISH_DATE>,
            "categoryEntity": {
                "id": <CATEGORY_ID>,
                "name": <CATEGORY_NAME>
            },
            "pickerEmail": <PICKER_USER_EMAIL>,
            "available": true
        },
        "createdAt": <TIMESTAMP>,
        "updatedAt": <TIMESTAMP>
    }
]
```