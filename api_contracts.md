## API contracts

### APIs  

#### Save Movie name

##### URI
``
POST /movie-name
``

##### body
```
{
  "message": "moview_name",
  "error": {
    "code": "error code",
    "message": "error message"
  }
}
```

##### Success response
http code: `200`

##### Error response due to invalid request field values
http code: `400`
```
{
    "success": false,
    "errors": [
        {
            "code": "INVALID_REQUEST"
        }
    ]
}
```