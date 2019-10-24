#Description: User Add items to cart and proceed to submit order,verify them in debugorders and inventorycheck
@TRTEPAX-42 @WIP
Feature: Submit order flow - Submit to Fulfillment

Background: 
    Given I launch Epax Website
    And any Category L2 page

@TRTEPAX-384
Scenario: Scenario2-Submit Order to the Fulfillment Process
    Given a Mini Cart where the user has tapped the Submit Order button
    When the inventory check returns as adjustment is needed for Zero items
    Then the Submit Order button continues to display a pending visual state 
    #(e.g. spinner / "Submitting order..." or similar)
    And the Submit Order button remains in an inactive state
    #(cannot be tapped)
    And the Cart submits the order to the Airside Server
    And the Cart awaits an acknowledgement from the Fulfillment Process

#@TRTEPAX-42
#Scenario: Submit order flow - Submit to Fulfillment
    #When user add Two items to cart from LTwo Page
    #And the user taps the Cart icon
    #When the Submit Order button is active
    #And the user taps the Submit Order button
    #Then the Submit Order button displays a pending visual state "Submitting Order"
    #And the Submit Order button is in an inactive state cannot be tapped
    #And the Cart submits the order to the Fulfillment Process
    #And the Cart awaits response from the Fulfillment Process

#@TRTEPAX-385 @Vieworderisnotyetimplemented
#Scenario: scenario3-Submit Order acknowledged
    #Given a Mini Cart in the Submit Order flow and the order submission is awaiting acknowledgement from the Fulfillment Process
    #When the Fulfillment Process acknowledges receipt of the order to the order
    #Then the Mini Cart displays a success notification to the user
    #And the Submit Order button is replaced by a View Order button and a Continue Shopping button

#TBD: do we clear the cart view at this point or retain the order until the user navigates away from the cart???
#@TRTEPAX-387 @Vieworderisnotyetimplemented
#Scenario Outline: scenario4-View Order / Continue Shopping
    #Given a Mini Cart where an order has been acknowledged as successfully submitted to the Fulfillment Process
    #When the user taps the [Cart Success] button
    #Then the shop shall navigate to the [Cart Dest] page

    #Examples: 
      #| Cart Success      | Cart Dest                    |
      #| View Order        | My Orders                    |
      #| Continue Shopping | last known state in the shop |

#Scenario: Scenario 5:  Fulfillment Process unresponsive
    #Given a Mini Cart in the Submit Order flow
    #When the Fulfillment Process is unresponsive during inventory check or order submission
    #Then the Submit Order button shall display an error message (example: "Could not submit order, please try again") for 1.5 seconds
    #And then revert to the default Mini Cart view/state
#
#@TRTEPAX-389
#Scenario: scenario6-Inventory adjustment is needed
    #Given a Mini Cart in a Submit Order flow
    #When the inventory check returns as adjustment is needed for 1 or more items
    #Then then Cart Qty is adusted for every Cart Item
    #And the Mini Cart view is updated to display the new Cart Qtys
    #And a message is displayed to inform the user about items that changed
    #Cart Qty changed to 0: "[Item] removed because no longer in stock
    #Cart Qty changed to 1 or more: [Item] quantity reduced to [new qty]
    #And the Submit Order button is replaced by "Back to Shop" and "Accept & Submit" buttons
#
#@TRTEPAX-390 @yettoimplement
#Scenario Outline: Scenario 7- Back to Shop / Accept & Submit
    #Given a Mini Cart in a Submit Order flow
    #And inventory adjustment is needed for 1 or more items
    #When the user taps the [Next Option] button
    #Then the app shall proceed to [Next Action]
#
    #Examples: 
      #| Next Option     | Next Action                                                                             |
      #| Back to Shop    | dismiss the Mini Cart overlay and return the user to the last know position in the shop |
      #| Accept & Submit | submit the order to the Fulfillment Process                                             |
#
#@TRTEPAX-429
#Scenario: Scenario 8: Exiting Mini Cart while in Submit flow
    #Given a Mini Cart in a Submit Order flow
    #When the user uses any control to dismiss the Mini Cart
    #(for example: click outside, X button)
    #Then the Cart finishes the current action
    #And the Mini Cart view will not persist any state of the Submit flow
