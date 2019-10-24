#Works on All Devices
@TRTEPAX-67
Feature: EPAX UI Test - Mini Cart in the onboard shop

  Background: 
    Launch Epax Website and navigate to a category page and add an item to cart
    Given I launch Epax Website

  #Scenario:1
  Scenario: Mini Cart - Access the Mini Cart
    Given an onboard shop where the Cart icon is visible
    When the user taps the Cart icon
    Then the Mini Cart overlay shall slide in from the right
    And the Mini Cart overlay shall persist for further user interaction

  #Scenario:2 &3
  Scenario: Mini Cart  - Empty state
    Given a Mini Cart that is open
    When the Mini Cart contains zero items
    Then the Mini Cart shows an empty state icon
    And the Mini Cart shows an empty state message
    And the Mini Cart footer shows only a Continue Shopping button

  #Scenario:6
  Scenario: Mini Cart - Display Items
    And any Category L2 page
    And the user taps the Item name
    When the Add to Cart button is active
    And the user taps the Add to Cart button
    Given a Mini Cart that is open
    And the Mini Cart contains an Item
    Then the Mini Cart shall display a Cart Item element for the corresponding Item

  #Scenario:4
  @TRTEPAX-277
  Scenario: Mini Cart - Close the Mini Cart
    Given a Mini Cart that is open
    When the user taps the close button
    Then the Mini Cart overlay shall dismiss
