Feature: Testing new application

  @start
  Scenario: User is able to open the home page
    Given check urlss
    When redirects the link
    Then open the home page
