@TRTEPAX-131 
Feature: I want to have Category L1 pages 
Background: Launch Epax Website and navigate to a category page 
	Given I launch Epax Website 
@DesktopOnly 
Scenario: Displaying Category highlights 
	Given a Category LOne page 
	When CatLTwo is a Category LTwo within the current Category LOne 
	Then the page shall include a Category highlight for CatTwo 
	And the ordering of the Category highlights is A-Z based on Category name 
	
	#Examples:
	#|CatL2|
	#|Cold Drinks|
	#|Hot Food|
	#|Merchandise|