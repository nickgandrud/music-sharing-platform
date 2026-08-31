DTO Service Flow to the DB:

POST/PUT/ETC JSON

↓

CreateContentRequest/UpdateContentRequest

↓

ContentService

↓

Unpacks/Unwraps to Content

↓

ContentRepository

↓

PostgreSQL

--------------------
DTO Service Flow To Client:

PostgreSQL

↓

Content

↓

Wraps to ContentResponse

↓

Controller

↓

JSON