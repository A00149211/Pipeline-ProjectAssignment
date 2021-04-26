Feature: The Create Book Page
  As a librarian
  I want to add a book to the library
  So that the book is visible to members

  Background:
    Given the user is on the list books page

  @wineadded
  Scenario: The user should be able to add a book with valid values
    When the user enters valid book details
    And user clicks on create button
    Then the user enters valid book details
    And user clicks on create button
    Then user should see be redirected to books page