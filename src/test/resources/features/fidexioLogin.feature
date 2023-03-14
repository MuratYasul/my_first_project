#execution
@regression
@FIDEX10-334 @wip @smoke
Feature: Login functionality
  Background: user is on the login page
    Given user is on the login page

  #Test Case 1 Scenario:
  @FIDEX10-414 @wip
  Scenario Outline: User can login with valid credentials
    When user enters valid "<email>"
    And user enters password "<password>"
    And user clicks on the login button
    Then user should be on the homepage

    Examples: Email and Password
      | email                   | password     |
      | salesmanager44@info.com | salesmanager |
      | posmanager33@info.com   | posmanager   |


#Test Case 2 Scenario:
@FIDEX10-415 @wip @test1
  Scenario Outline: User cannot login with invalid credentials
    When user enters invalid email "<email>"
    And user enters invalid password "<password>"
    And user clicks on the login button
    Then user sees "Wrong login/password" message displayed for invalid credentials

    Examples: Email and Password
      | email                    | password     |
      | salesmanager440@info.com | salesmanager |
      | salesmanager44@info.com  | sales        |
@FIDEX10-416 @wip
#Test Case 3 Scenario
  Scenario Outline: User should see validation message when using empty credentials
    When user enters valid "<email>" in username box
    And user enters valid "<password>" in password box
    And user clicks on the login button
    Then user should see "Please fill out this field." validation message
    Examples:
      | email                 | password     |
      |                       |              |
      | posmanager33@info.com |              |
      |                       | salesmanager |

@FIDEX10-417 @wip
    #Test Case 4
  Scenario: User should see the password in bullet signs by default
    When user enters password "<password>"
    Then user should see bullet signs

@FIDEX10-419 @wip
    #Test Case 5 Scenario
  Scenario Outline: Verify "Enter" key of the keyboard is functional
    When user enters valid "<email>"
    And user enters password "<password>"
    And user press ENTER key on the login button
    Then ENTER key is pressable

    Examples: Email and Password
      | email                   | password     |
      | salesmanager44@info.com | salesmanager |
      | posmanager33@info.com   | posmanager   |
