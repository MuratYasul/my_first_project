#execution

Feature: Login functionality
  Background: user is on the login page
    Given user is on the login page

  #Test Case 1 Scenario:
  Scenario Outline: Login with valid credentials
    When user enters valid "<email>"
    And user enters password "<password>"
    And user clicks on the login button
    Then user should be on the homepage

    Examples: Email and Password
      | email                   | password     |
      | salesmanager44@info.com | salesmanager |
      | posmanager33@info.com   | posmanager   |


#Test Case 2 Scenario:
  @wip
  Scenario Outline: Enter invalid credentials
    When user enters invalid email "<email>"
    And user enters invalid password "<password>"
    And user clicks on the login button
    Then user sees "Wrong login/password" message displayed for invalid credentials

    Examples: Email and Password
      | email                    | password     |
      | salesmanager440@info.com | salesmanager |
      | salesmanager44@info.com  | salesmanage  |
      | posmasmanager44@info.com | posmanager   |
      | posmanager33@info.com    | posmanage    |

    #Test Case 3 Scenario
  Scenario Outline: Enter key functionality
    When user enters valid "<email>"
    And user enters password "<password>"
    And user press ENTER key on the login button
    Then ENTER key is pressable

    Examples: Email and Password
      | email                   | password     |
      | salesmanager44@info.com | salesmanager |
      | posmanager33@info.com   | posmanager   |
