# Direct SQL

## Background

Tired of opening your favorite (or not !!) SQL Client ? only to run the same SQL query
over and over again ?. At least that what happened to me. I think, I'm worst than that
I almost never save my queries on an sql file. If I did that, I didn't managed my sql files
properly. So I ended to rewrite it again and again. If you are just like me, then this tools is for you.
 
Well is not exactly like that, in many places that I have working before. We always have
a list of queries that we are use all the times. These queries are quite simple for most of 
the time. And use the same format.

DirectSql can help you to keep those queries and show it directly the results in the form 
of web application. So it will be very easy to access.

## How to install DirectSQL

### Prerequisite

Java 8 installed

### Run DirectSQL

We ship DirectSQL in a tar file that can be download from this release page. Once it is downloaded
you can un-tar it. The structure of un-tar DirectSQL is :

```
- DirectSQL
    - bin    // (1)
    - conf   // (2)
        - directsql 
    - repo   // (3)
```    

For each of the folder we have :

- _bin_ : contains the executable for Windows (directsql.bat) and Nix (directsql)
- _conf/directsql_ : contains the configuration files which are jdbc and queries
- _repo_ : contains dependencies 

To run the application, you just need to launch from the bin : directsql (.bat or without extension)

### Configuration
 
In the directsql/conf we have put two dummy configuration files as an example :

- jdbc-datasource.yml : the datasource(s) configuration
- test-query.yml : one query example

#### jdbc-datasource

We put all the datasource configuration in this file
```
-- example
datasource1:
    driverClassName: org.h2.Driver
    url: jdbc:h2:tcp://localhost/~/test
    username: sa
    password:

...
datasource2:
    driverClassName: org.h2.Driver
    url: jdbc:h2:tcp://localhost/~/test
    username: sa
    password:
```

the name of the properties are self explanatory.

#### \*-query.yml

The query configured in the \*-query.xml files. Each file contains one query.

#### Simple query

For a very simple query, we can write it like this.

```
datasource: datasource1     \\ (1)
name: query1               \\ (2)
description: This is a query 1 description \\ (3)
mainSql: |                                  \\ (4)
        Select ID, NAME from TEST
```


1. Datasource used for this query
2. The query name
3. Short description for this query
4. The main query

#### Advanced query

If we want to limit the query, and still able to add some filter, we
can write it like this.

```
datasource: datasource1
name: address
description: This is address table query contains a lot of data
mainSql: |
        Select ID, NAME, COUNTRY
        from Address
        {{WHERE}}  // (1)
        limit {{LIMIT}} offset {{OFFSET}} // (2)

detailSql: |
        Select ID, NAME, PHONE, ADDRESS, CITY, COUNTRY
        from Address
        Where ID = {{ID}} // (3)
```

1. WHERE : will be replaced by Where and its filter clause.
2. LIMIT and OFFSET : will be replaced by limit and offset of a query
3. detailSql : the data from the main SQL (for this example is ID) is used
   as a filter element in the detailSQL.
   
WARNING : the detailSQL result is limited to one.

