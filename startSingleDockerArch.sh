#!/bin/sh
#docker rm -f books_db
#docker run -d -p 1521:1521 -p 80:80 --name books_db funthomas424242/books.db

docker rm -f books_app
docker run -d -p 8080:8080 --link books_db:books_db --name books_app funthomas424242/books.app

docker rm -f books_web
docker run -d -p 9000:9000  --link books_app:localhost --name books_web funthomas424242/books.web

