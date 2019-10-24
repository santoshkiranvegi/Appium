@TRTEPAX-134 
Feature: EPAX UI Test- Verify L1 and L2 Page Navigation

  Background: 
    Given I launch Epax Website
    When user click on Retails button on HomePage
	
	
  @DesktopOnly
  Scenario Outline: Category highlight, Scenario1 Link to Category L2
    Given a Category highlight for <CatL2>
    When the user taps the SeeMore link for <CatL2> catl2
    Then the app shall transition to the Category L2 page for <CatL2>

    Examples: 
      | CatL2          |
      | Alcohol        |
      | Cold Drinks    |
      | Fresh Food     |
      | Hot Drinks     |
      | Merchandise    |
      | Savoury Snacks |
      | Sweet Snacks   |
      | Virtuals       |


  @TRTEPAX-261 @WIP
  Scenario: Category highlight Scenario2- Order of Item cards
    Given a Category highlight for a Subcategory in L1Page
    When the Item cards for the Category highlight are displayed
    Then the Item cards shall be displayed and ordered by qty available, starting with highest qty
