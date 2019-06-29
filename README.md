-----
About
-----

This is a database driven application that was created to store information for a movie shop. 

A simple database schema was created using PostgreSQL. A relational database is apt because there are relationships
between customers, movies, and shipments.

A simple java client application was implemented to collect data such that the database can store important information.

**WARNING**: Must create database, secure connection, and install drivers        

-------------
Configuration
-------------

Recommended Operating System: Linux

Software Required: PostgreSQL JDBC driver (postgresql-9.4-1201)

------------
Installation
------------

Download PostgreSQL JDBC driver from https://jdbc.postgresql.org/download.html

--------------------------
**Operating Instructions**
--------------------------
1. Create database with movie_shop.sql
   **Linux:** 
              *createdb -U <username> -W -h 127.0.0.1 <database_name>*
              *psql -U <username> -W -h 127.0.0.1 <database_name>*

2. Download PostgreSQL JDBC driver and extract the files to create two directories (META-INF and org)
   **Linux:** 
              *jar -xf postgresql-9.4-1201.jdbc4.jar*

3. Edit Movie_shop.java so it contains your username, password, and database name

4. Compile Movie_Shop.java to create Movie_Shop.class
   **Linux:** 
              *javac Movie_Shop.java*
              
5. Run application by invoking Java Virtual Machine using the java application
   **Linux:** 
              *java Movie_Shop*
             
6. You will be prompted to enter the username and password to secure the connection

-------------
File manifest
-------------
Includes README.md, Movie_Shop.java, movie_shop.sql

-----------------------------------
Copyright and Licensing information
-----------------------------------
Movie_Shop.java, movie_shop.sql is public domain software - free and open source

-------
Queries
-------
For any queries please contact Jon Peppinck at mrjonpeppinck@gmail.com


