Feature: Cart actions

  Scenario: Add to cart and remove from cart
    When add three products to cart
    Then can remove three products from cart