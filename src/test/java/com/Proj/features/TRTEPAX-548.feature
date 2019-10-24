#Works on All Devices
@TRTEPAX-548 
Feature: Shop Navigation Breadcrumbs hierarchical
Background: Launch Epax Website and navigate to a category page
	Given I launch Epax Website
	And any Category L2 page
	
Scenario: Breadcrumbs on Catalogue pages
	Given a Category Page Page Type
	When the user accesses the page
	Then the page shows breadcrumb navigation as Breadcrumbs
	And the breadcrumb navigation elements link to the respective pages
	
#Examples:
#|Page Type|Breadcrumbs|
#|Category LOne page|Home - Category LOne|
#|Category LTwo page|Home - Category LTwo - Category LTwo|
#|Item page|Home - Category LOne - Category LTwo|