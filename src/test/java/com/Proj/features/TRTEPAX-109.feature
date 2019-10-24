#Works on All Devices
@TRTEPAX-109 
Feature: EPAX - show Item cards on pages in the onboard shop

  Background: Launch Epax Website and navigate to a category page
    Given I launch Epax Website
    And any Category L2 page

  @TRTEPAX-112
  Scenario: Item card - Sc. 1 - Tapping the Item card
    Given An Item card on any page in the onboard shop
    When The user taps the Item card
    Then The app transitions to the Item page for the Item displayed on the Item card

  @TRTEPAX-113
  Scenario: Item card - Sc. 2 - Add to Cart from Item card
    Given An Item card on any page in the onboard shop
    When The user taps the Item card
    When the user taps the Add to Cart button on the Item card
    Then 1 unit of the Item is added to the Cart