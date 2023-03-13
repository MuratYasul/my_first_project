Feature: Logout functionality
  Background: user in on the login page
    Given user is on the login page

    @smoke
  Scenario Outline: User can log out on the login page
    When user enters valid "<email>"
    And user enters password "<password>"
    And user clicks on the login button
    And user should be on the homepage
    And user clicks on the username
    Then user clicks on the logout button

    Examples: Email and Password
      | email                   | password     |
      | salesmanager44@info.com | salesmanager |
      | posmanager33@info.com   | posmanager   |

