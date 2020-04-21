# stockAlarms

For all requests set the Content-Type header to "application/json"

Usage:

<b>Register user</b>

POST http://localhost:5000/user/register

Body:
{
	"firstName" : "victor",
	"lastName" : "tanasa",
	"password": "abc123",
	"email" : "victor.tanasa@gmail.com"
}


<b>Login user</b>

POST http://localhost:5000/login

Body:
{
	"email" : "victor.tanasa@gmail.com",
	"password": "abc123"
}

Returns Bearer authorization token on response headers.


<b>Create stock</b>

POST http://localhost:5000/stock/create

Header: Authorization + Bearer value

Body:
{
	"name" : "IBM",
	"price": "122.3"
}


<b>List all stocks</b>

GET http://localhost:5000/stock/list

Header: Authorization + Bearer value

Returns:
[
    {
        "id": 97,
        "name": "IBM",
        "price": 99.6
    },
    {
        "id": 98,
        "name": "GOOG",
        "price": 200.1
    }
]


<b>Create alarm</b>

POST http://localhost:5000/alarm/create

Header: Authorization + Bearer value

Body:
{
	"stock" : {
		"id" : 97
	},
	"baseStockPrice" : 90,
	"percentageThreshold" : 4.2,
	"alarmType" : "OVER_THRESHOLD",
	"enabled" : true
}


<b>Update alarm</b>

PUT http://localhost:5000/alarm/{alarmId}

Header: Authorization + Bearer value

Body:
{
	"stock" : {
		"id" : 97
	},
	"baseStockPrice" : 90,
	"percentageThreshold" : 4.2,
	"alarmType" : "OVER_THRESHOLD",
	"enabled" : false
}

If a field is omitted, it will not be updated.


<b>Delete alarm</b>

DELETE http://localhost:5000/alarm/{alarmId}

Header: Authorization + Bearer value


<b>List alarms</b>

GET http://localhost:5000/alarm/list

Header: Authorization + Bearer value

Returns:

[
    {
        "id": 99,
        "baseStockPrice": 100,
        "percentageThreshold": 7.5,
        "alarmType": "OVER_THRESHOLD",
        "enabled": true,
        "stock": {
            "id": 98,
            "name": "GOOG",
            "price": 200.1
        }
    },
    {
        "id": 100,
        "baseStockPrice": 106,
        "percentageThreshold": 2.5,
        "alarmType": "OVER_THRESHOLD",
        "enabled": true,
        "stock": {
            "id": 97,
            "name": "IBM",
            "price": 99.6
        }
    }
]