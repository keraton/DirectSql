# Specification DirectSQL

## Introduction

We want to create an application that can be used to expose SQL Result set 
directly into a web page (or maybe REST results)

## Architecture

We are using spring boot to speed up our development. 
 
## Layer

The possible layer can be described like this

.yaml file ->  Database -> DAO (jdbc) -> Map ResultSet to JavaObject -> Map to REST -> Webpage Javascript
                                                                    -> Map to Thymyleaf -> Static Webpage
                                                       
## Layer Details
                                                       
### yaml files

#### yaml Connection

yaml file that will contains the information of JDBC connection such as
* The connection name 
* url + port
* schema name
* Jdbc driver name
* username and password (ho ho ho)

#### yaml Query 

yaml file that will contains SQL query 
* The Connection that it used
* The name of the query
* The description of the query
* The main SQL query
* The detail SQL query (this will be done later) 

#### yaml mapping
 
From a specific yaml format we will create a java object that will hold
of all information mentioned.

### Database

This should be the database that contains data to be get

### DAO (Data Access Object)

#### ResultSet extraction 

This part is responsible to 
* Create the query
* Execute the query
* Get the resultSet

#### ResultSet mapping to GenericJavaObject

The resultSet should be map to a generic object that will hold
* The Header of result set
* The data

### Controller

#### Rest Controller

Expose the Generic object to an REST api

#### MVC Controller

Expose the Generic object to a Tymyleaf template


