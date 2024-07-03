@tag
Feature: Purchase the product from ecommerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on the ecommerce page

  @tag2
  Scenario Outline: Positive test of submiting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage

    Examples: 
      | name  										| password 		| productName |
      | sachin.waghmare@gmail.com | Sachin@123 	| ZARA COAT 3	|
