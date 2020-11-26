Feature: Validate Country Capitals

  Scenario Outline: Validate the count of countries
    Given I trigger the get request for countries
    When I receive the successful response <responseCode> from request
    Then I validate the <count> of countries
    Examples:
    |responseCode|count |
    |200         |250   |

  Scenario: Currency code received in Countries API matches currency code in Capital API
    Given I trigger the get request for countries
    Then I receive the successful response 200 from request
    When I extract the capital name from response
    When I get the capital details
    Then I receive the successful response 200 from request
    Then I validate the currency in the response

  Scenario:Passing invalid capital name returns 404 status
    Given I trigger the get capital details for "InvalidCapitalName"
    Then I receive the successful response 404 from request

