@TRTEPAX-68  @MobileOnly
Feature: EPAX - As a PAX I want to have a side menu in the shop - On Mobile

Background: Launch Epax Website
	Given I launch Epax Website

#Scenario: 2
@TRTEPAX-147 
Scenario: Displaying the Side menu
	Given Any page in the shop where the Side menu icon is present
	When the user taps the Side menu icon
	Then the Side menu overlay shall appear	
	And the Side menu is vertically scrollable if necessary to accommodate the viewport
	And expandable categories are visually identified

#Scenario: 3	
#Works on All Devices
@TRTEPAX-148
Scenario: Closing the Side menu
	Given a Side menu that is open
	When the user taps the X button
	Then the Side menu overlay shall dismiss

#Scenario: 1
@TRTEPAX-149
Scenario: Expanding a Category L1 in the Side menu
	Given a Side menu that is open	
	When the user taps Cat LOne in the Side menu
	Then the menu displays a list of Cat LTwo
	And the Side menu overlay shall persist for further user action

#Scenario: 6	
@TRTEPAX-150
Scenario: Collapsing a Category L1 in the Side menu
	Given a Side menu that is open with a Category LOne expanded
	When the user taps a Category LOne name at the top of the list
	Then the Side menu shall revert back to its base state
	And the Side menu overlay shall persist for further user action

#Scenario: 4	
@TRTEPAX-151
Scenario: Navigating to a Category L2 page
	Given a Side menu that is open with a Category LOne expanded
	When the user taps a Category LTwo in the Side menu
	Then the Side menu shall dismiss
	And the app shall navigate to the Category page for the applicable Category LOne
	
#Scenario: 5
#  Scenario:  Navigating to other pages/sections	-->Covered in Story-133