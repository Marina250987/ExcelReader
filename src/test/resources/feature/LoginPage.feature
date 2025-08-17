Feature: Testing Login Page
  Background:
  Given Set up driver

    Scenario Outline: Testing successful login

      When Opening Login Page
      And enter email "<email>"
      And enter password "<password>"
      And click on login button
      Then check successful login to the user account
      Then quit driver
      Examples:
        | email                     | password |
        | testovyiivan@mail.ru      | 1234567R |


  Scenario: Check empty fields validation
    When Opening Login Page
    And click on login button
    Then email required validation message is displayed
    And password required validation message is displayed
    And login button is inactive
    And user stays on login page
    Then quit driver

  Scenario: Check registration link
    When Opening Login Page
    And click on registration link
    Then user is redirected to registration page
    Then quit driver