@TRTEPAX-21 
Feature: EPAX UI Test - Item Page Core
Background: Launch Epax Website and navigate to a category page
  Given I launch Epax Website
  And any Category L2 page

Scenario: Item page - Out of stock behavior
  Given an Item page out of stock
  When the corresponding item is Out of Stock
  Then the Add to Cart button is greyed out
  And the Add to Cart button is inactive not clickable, does not add the Item to the Cart
  And an Out of Stock tag is show on the Item page

Scenario: Item page -  Cart Controls
  Given an Item page which is active
  When the Add to Cart button is active
  And the user taps the Add to Cart button
  Then the Add to Cart button provides visual confirmation of the action
  And one unit of the Item is added to the Cart

@MobileOnly
Scenario: Item page - Sticky Cart controls on mobile viewport
  Given an Item page in mobile view
  When the user scrolls the view so that the Cart controls move off screen at the top
  Then a Cart control overlay section shall appear on the bottom of the viewport
  And the Cart control overlay shall be persistently visible above the scrolling view
  
#Took the Item which is having Show Less and Show More
Scenario: Item Page - Expand or collapse Description section
	Given an Item page
	Then the default state on page load is Show More
	When the user taps the [Description control] button
	Then the page shall [Description behavior]
	
#Examples
#|Description control|Description behavior|
#|Show more|expand to show the full Description section|
#|Show less|collapse to show only the first lines of the Description section|