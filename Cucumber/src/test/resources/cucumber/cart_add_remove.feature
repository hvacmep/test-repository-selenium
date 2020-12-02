Feature: Cart actions

  Scenario: Add to cart and remove from cart
    When add '4' products to cart
    Then cart is not empty
    When remove all products from cart
    Then cart is empty