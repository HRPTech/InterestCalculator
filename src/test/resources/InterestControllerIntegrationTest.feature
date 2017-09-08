Feature: Integration test for a Interest Controller

  Scenario: Verify a value with parameters
    Given I send a "/api/calculation/interest/123LR_HIRAN" request
    When I see a success status code value 200
    Then I verify a value "20.36"