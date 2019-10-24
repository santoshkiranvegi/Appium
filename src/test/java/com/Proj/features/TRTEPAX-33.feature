@TRTEPAX-33  @WIP
Feature: EPAX UI Test - Category L2 pages sorting

  Background: launch Epax
    Given I launch Epax Website
    And any Category L2 page

  Scenario: Category L2 Page - Default sort order
    When the user has not yet made any changes to the sort order
    Then the default sort order of "Product name (A-Z)" is applied

  Scenario Outline: Category L2 Page - Changing the sort order
    Given a Sort overlay that is open on a Category L2 page
    When the user selects the <sort> option
    Then the sort overlay shall dismiss
    And the display order of the Items in the Category L2 page shall change to <sort>
    And the secondary sort will be <secondary sort>

    Examples: 
      | sort               |  | secondary sort   |
      | Product name (A-Z) |  | Not Applied      |
      | Product name (Z-A) |  | Not Applied      |
      | Price Ascending    |  | Price Ascending  |
      | Price Descending   |  | Price Descending |

  #Dev Pending --> methods to be Implemented
  Scenario: Category L2 Page -  Current sort order in selector
    Given any Category L2 page
    When the user taps the sort selector
    Then the sort overlay appears
    And the current sort order is selected as active
