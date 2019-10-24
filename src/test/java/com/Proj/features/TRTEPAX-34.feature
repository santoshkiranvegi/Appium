@TRTEPAX-34  @WIP

Feature: EPAX UI Test - Category L2 page filtering

  Background: 
    : Launch Epax Website and navigate to a category page and add an item to cart

    Given I launch Epax Website
    And any Category L2 page

  # To Verify for Recommendations are displayed
  @TRTEPAX-123
  Scenario Outline: Category L2 page - Filtering by Item price range
    Given a Filter overlay that is open on a Category L2 page
    When the user enters a combination of "<low>" and "<high>" price limits
    And "<low>" must be less than equal to "<high>"
    And the user taps the Apply button
    Then the Filter overlay dismisses
    And the Category L2 page filters the displayed Items to those which have a price between "<low>" and "<high>"

    Examples: low high
      | low | high |
      |   0 |   10 |
      |  10 |    0 |
      |   5 |   20 |

  Scenario: Category L2 page - Filtering by Deal Available
    Given a Filter overlay that is open on a Category L2 page
    When the user selects "Deal Available" filter criteria
    And the user taps the Apply button
    Then the Filter overlay dismisses
    And the Category L2 page filters the displayed Items to those which are eligible at least "1item" with "Deal"

  Scenario: Category L2 page - Filtering by Allergens Available
    Given a Filter overlay that is open on a Category L2 page
    When the user selects "Eggs" filter criteria
    And the user taps the Apply button
    Then the Filter overlay dismisses
    And the Category L2 page filters the displayed Items to those which are eligible at least "1item" with "Eggs"
