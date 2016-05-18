Feature: Queries page feature

  In this page we can :
  * List all the queries
  * It will show the first query by default

  We have two types of queries :
  * Simple query : basically it just a query without limit and offset (first query)
  * Limited-size query : a query with a limit (second query)

  Queries can contains details which mean that when user click at a one row it will redirected to a detailed page.

  @Ready
  Scenario: User open queries page
    When user access the queries page
    Then load queries: person, salary
    And  the first query is selected
    And  the table contains data

  @Ready
  Scenario: User select second query
    Given user access the queries page
    When user select salary query
    Then the salary query is selected
    And the table contains data

  @Ready
  Scenario: User open second query directly
    When user access the query salary page
    Then the salary query is selected
    And the table contains data

  @Ready
  Scenario: User filter data for the simple query
    Given user access the queries page
      And the table contains columns
            | ID | NAME |
     When user filter with name is equal to Axel
     Then the table contains only Axel

  @Ready
  Scenario: User filter data for the size limited query
    Given user access the query salary page
    And the table contains columns
            | ID | TITLE | LAST_NAME |
     When user filter with last_name is equal to Ford
     Then the table contains only Ford
      And the address bar contains: last_name, Ford

  @Ready
  Scenario: User limit data for the size limited query
    Given user access the query salary page
     When user limit row to 1
     Then the table size is 1

  @Ready
  Scenario: User open filtered query
     When user access the query salary page with filter last_name is equal to Ford
      And the table contains columns
            | ID | TITLE | LAST_NAME |
     Then the table contains only Ford

  @Ready
  Scenario: User open info page
    Given user access the queries page
     When user click at info link
     Then user redirected to info page

  @DevReady
  Scenario: User open query detail
    Given user access the query salary page
      And the table contains columns
            | ID | TITLE | LAST_NAME |
     When user click on ID equal to 2
     Then user redirected to detailed page
      And detailed page contains
            | ID        | 2                            |
            | TITLE     | Structural Analysis Engineer |
            | LAST_NAME | Perez                        |
            | EMAIL     | lperez1@paginegialle.it      |
            | GENDER    | Male                         |
            | COMPANY   | Babbleopia                   |


