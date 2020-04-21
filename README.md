# stockAlarms

For all requests set the Content-Type header to "application/json"

Usage:

1) Register user

POST http://localhost:5000/user/register
Body:
{
	"firstName" : "victor",
	"lastName" : "tanasa",
	"password": "abc123",
	"email" : "victor.tanasa@gmail.com"
}


2) Login user

POST http://localhost:5000/login
Body:
{
	"email" : "victor.tanasa@gmail.com",
	"password": "abc123"
}

Returns Bearer authorization token on response headers.


3) Create stock:

POST http://localhost:5000/stock/create
Header: Authorization + Bearer value
Body:
{
	"name" : "IBM",
	"price": "122.3"
}


4) List all stocks:

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


5) Create alarm:

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


6) Update alarm:

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

If a field is ommited, it will not be updated.


7) Delete alarm:

DELETE http://localhost:5000/alarm/{alarmId}


8) List alarms:

GET http://localhost:5000/alarm/list
Header: Authorization + Bearer value
