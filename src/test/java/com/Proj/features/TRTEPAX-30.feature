#Works on All Devices
@TRTEPAX-30
Feature: EPAX UI Test - Item Page - Recommendations and Substitutions

  Background: Launch Epax Website
    #Given load EZY data
    Given I launch Epax Website

  Scenario: Item page - Recommendations empty state
    When navigate to required Page for "Retail Items-->Alcohol-->Johnnie Walker Black"
    #When the user taps the Item name
   	Given an Item page for "Johnnie Walker Black"
    When there are no Item Recommendations configured for [Current Item]
    Then the Item page shall not show a section for Recommendations
    
  Scenario: Item page - Substitutions empty state
    When navigate to required Page for "Retail Items-->Alcohol-->Johnnie Walker Black"
    Given an Item page for "Johnnie Walker Black"
    When there are no Item Substitutions configured for [Current Item]
    Then the Item page shall not show a section for Substitutions

  Scenario: Item page - Recommendations section
  	When navigate to required Page for "Retail Items-->Alcohol-->Bundaberg Rum"
    Given an Item page for "Bundaberg Rum"
    When [R-Item] is an item that is configured as an Item Recommendation for [Current Item]
    Then the Item page shall contain a section for Recommendations
    And the Recommendation section shall display an Item card for [R-Item]

  Scenario: Item page - Substitution section
    When navigate to required Page for "Retail Items-->Alcohol-->Bundaberg Rum"
    Given an Item page for "Bundaberg Rum"
    When [S-Item] is an item that is configured as an Item Substitution for [Current Item]
    Then the Item page shall contain a section for Substitutions
    And the Substitution section shall display an Item card for [S-Item]
  