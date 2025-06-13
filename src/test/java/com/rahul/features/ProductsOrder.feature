Feature: Order Product on E-commerce Site

@placedOrder
  Scenario Outline: Order Product on RahulShettyAcademy E-commerce Site
    Given Im Login as "svcSpecialist"
    And I should see message "Login Successfully"
    When I add  "<Product>" to add to cart
    #Then I should see message "Product Added To Cart"
    When I click on "Cart" button
    And Verify "<Product>" "IN STOCK"
    And I "Checkout" The "<Price>" of  "<Product>"
    And I Filled The Shipping Address
    And I click on "Place Order" button
    Then I should see message "Order Placed Successfully"
    Then I should see success message saying "THANKYOU FOR THE ORDER."

    Examples: 
      | Product         | Price   |
      | ADIDAS ORIGINAL | $ 31500 |

@checkOrder
  Scenario Outline: Verify Order Id is populated for product which is placed
    Given Im Login as "svcSpecialist"
    And I should see message "Login Successfully"
    When I click on "ORDERS" button
    Then I should see "<Product>" is Displaying with It's OrderId which is Placed 

    Examples: 
      | Product         |
      | ADIDAS ORIGINAL |
      