#Works on All Devices
@TRTEPAX-17 
Feature: EPAX UI Test - Category L2 Page - show items arranged category
Background: Launch Epax Website and navigate to a category page 
	Given I launch Epax Website 
	And any Category L2 page 
	
Scenario: Category L2 page - Item Out of Stock
	Given an Item Card on a Category L2 page 
	When the corresponding Item is Out of Stock 
	Then the Add to Cart button on the Item Card is greyed out 
	And the Add to Cart button on the Item Card is inactive 
	And an Out of Stock tag is show on the Item Card 
	
Scenario: Category L2 page - Infinite page scroll
	When the PAX scrolls to the bottom of the page
	And there are more Items to display in the category
	Then the Category L2 page shall load and display the next ten Items
	
Scenario: Category L2 page- End of page
	When the PAX scrolls to the bottom of the page
	And there are no further Items to display in the category
	Then the page shows a subtle visual hint that no further items can be loaded
	
Scenario: Category L2 page - Loading the first Items on the Category L2 page
	When the PAX accesses the page
	Then only the first Ten Items are displayed
