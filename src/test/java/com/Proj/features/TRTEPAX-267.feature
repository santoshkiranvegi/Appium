#Works on All Devices
@TRTEPAX-267 
Feature: EPAX - As a Passenger I want to have a Cart Item elements
Background: Launch Epax Website and navigate to a category page 
	Given I launch Epax Website 
	And any Category L2 page

@TRTEPAX-272
Scenario:  Decrementing to zero
	Given a Cart Item element
	When the Item's Cart Qty is 1
	Then the Qty button is in inactive visual state
	And the Qty button is functionally inactive
	
@TRTEPAX-268
Scenario: Line subtotal
	Given a Cart Item element
	When Item price is [price]
	And the Item's Cart Qty is [qty]
	Then the line subtotal shall be [price]x[qty]
	
@TRTEPAX-269
Scenario: Changing qty
	Given a Cart Item element
	When the user taps the [qty button] button
	Then the Cart Qty for that Item shall [qty change]
	And the Cart Item element qty display shall show the updated qty
	
@TRTEPAX-270
Scenario: Remove button
	Given a Cart Item element
	When the user taps the remove item button
	Then the Item shall be removed from the cart
	
@TRTEPAX-271
Scenario: Linking back to the Item
	Given a Cart Item element
	When the user taps the Item name in Cart Page
	Then the app shall transition to the Item page for the respective Item