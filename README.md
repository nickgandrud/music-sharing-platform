Music Sharing Plaform

This project is a proof of concept / practice project for me to conceptualize and put into practice of a springboot application.

The proof of concept is for a loosely concived "Music Sharing Platform" where various users share their favorite albums, songs, or mixes.
The users can only post objects that contain information about said favorite musical item and a link to it.
The users and content that they post are stored in a postgres sql database hosted on railway.
The apis that are used to retirve content and users will be included in this document.

Techstack:
Java 
Springboot
Soring Data JDBC
Postgres SQL
Railway




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