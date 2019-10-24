#Works on All Devices
@TRTEPAX-41
Feature: As a Crew I want PAX to confirm their seat number when submitting their order

  Background: Launch Epax Website and navigate to a category page and add an item to cart
    Given I launch Epax Website
    When the user taps the Cart icon
 
  @test1
  Scenario: Scenario1 Prompt user when no seat is entered
    Given that the user has not yet entered a seat number during the current sector
    Then the SEAT SELECTION is displayed
    And the input focus is on the Seat Row field
    And the Mini Cart is not visible
    And the Mini Cart is not accessible to the user

  @test2
  Scenario: Scenario2 Dismissing the Seat Selector
    Given a Seat Selector that is open
    When the user taps the X button
    Then the Seat Selector dismisses
    And any user inputs are discarded
    And the app returns to the last known state

  #blockedTS5
  #Scenario: Scenario3 Seat row placeholder hint
    #Given a Seat Selector
    #And [TS5 Row] is the maximum seat row number provided from TS5
    #When there is no user input in the seat row input field
    #Then the seat row input field shows a placeholder hint for input range of seat row as "1 - [TS5 Row]"

  #blockedTS5
  #Scenario: Scenario4 Seat row placeholder hint
    #Given a Seat Selector
    #And [TS5 Letter] is the maximum seat letter number provided from TS5
    #When there is no user input in the seat letter input field
    #Then the seat letter input field shows a hint for input range of seat row as "A - [TS5 Letter]"

  #blockedTS5
  #Scenario: Scenario5 Seat row validation
    #Given a Seat Selector
    #And [TS5 Row] is the maximum seat row number provided from TS5
    #When [Row Input] is the value entered by the user into the Seat Row field
    #Then the seat row is valid if [Row input] is in the 1 to [TS5 Row] range

  #blockedTS5
  #Scenario: Scenario6 Seat letter validation
    #Given a Seat Selector
    #And [TS5 Letter] is the maximum seat letter provided from TS5
    #When [Letter Input] is the value entered by the user into the Seat Letter field
    #Then the seat letter is valid if [Row input] is in the A to [TS5 Letter] range

	@test7
  Scenario: Scenario7 Visual validation
    Given a Seat Selector that is open
    Then the visual hint under the Sear field Bar color should be "grey"
    And the input text color for seat should be "grey"
    When the user enters Seat Number "1" and Seat Letter "A"
    Then the visual hint under the Sear field Bar color should be "green"
    And the input text color for seat should be "green"
    When the user enters Seat Number "99" and Seat Letter "Z"
    Then the visual hint under the Sear field Bar color should be "Amber"
    And the input text color for seat should be "Amber"


  @test8
  Scenario: Scenario8 Confirming a seat number
    And a Seat Selector that is open
    And the user enters a valid Seat Number and Seat Letter
    Then the Confirm Seat button becomes active

  @test9
  Scenario: Scenario9 Storing a seat number
    Given a Seat Selector where the Confirm Seat button is active
    When the user taps the Confirm Seat button
    Then the shop stores the seat number for the current sector
    And the Seat Selector dismisses
    And the Mini Cart is shown

  @test10
  Scenario: Scenario10 Editing a seat number
    Given a Mini Cart in default state
    When the user taps the edit seat number button
    Then the Seat Selector appears
    And the currently stored seat number is shown in the input fields
