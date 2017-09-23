@tag
Feature: Testing Login for Gmail
  @test1
  Scenario: Title of your scenario
    Given I want to write a step with precondition
    And some other precondition
    When I complete action
    And some other action
    And yet another action
    Then I validate the outcomes
    And check more outcomes

  @test2
  Scenario Outline: Checking G-mail login
    Given I launch website URL
    When I provide <username> and <password>
    Then I verify login is successful for <username>

    Examples: 
      | username | password |
      | kushalbhalaik@gmail.com    |       al | 
      | kushalbhalaik@gmail.com    |       al |

 
