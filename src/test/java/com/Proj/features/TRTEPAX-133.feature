#Description:
# Scenarios to be updated in QA Task
@TRTEPAX-133
Feature: I want to have a header menu in the shop (on laptops)

  Background: Launch Epax Website and navigate to a category page
    Given I launch Epax Website

  Scenario: Scenario-1 Non-touch devices use hover
    Given this scenario covered in "scenario-4"

	@MobileOnly
  Scenario: Scenario-2 Touch devices do not use hover
    Given a header menu in default state on a TOUCH device
    When the user taps L1Menuitem
    Then a Cat L2 dropdown for the selected Cat L1 is shown
    When the user taps the X button
    Then the Side menu overlay shall dismiss

	@DesktopOnly
  Scenario: Scenario-3 Navigating to a Cat L1 from the header menu
    Given a header menu with a Cat L2 dropdown shown
    When the user taps on a Cat L1 element again
    Then the Cat L2 dropdown dismisses
    And the app navigates to the corresponding Cat L1 page
	@DesktopOnly
  Scenario: Scenario-4 Non-touch devices use hover &  Navigating to a Cat L2 from the header menu
    Given a header menu with a Cat L2 dropdown shown
    When the user taps on a Cat L2 link
    Then the Cat L2 dropdown dismisses
    And the app navigates to the corresponding Cat L2 pages

  
  Scenario: Scenatio-5 Navigating to other pages/sections
    When user verify HeaderMenu fields
    And the user taps the "FAQ" menu element
    Then the app shall navigate to the "FAQ"
    When the user taps the "My Orders" menu element
    Then the app shall navigate to the "My Orders"
    When the user taps the "My Cart" menu element
    Then the app shall navigate to the "My Cart"
