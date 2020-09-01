Feature: Users uses console-based social networking application
 
  Scenario: Alice can publish messages to a personal timeline
    Given that the user/users Alice is/are using console-based social networking application
    When the user Alice write a post command Alice -> I love the weather today
    Then the message I love the weather today of the user Alice is pubblished

  Scenario: Bob can view Alice’s timeline
    Given that the user/users Alice and Bob is/are using console-based social networking application
    When the user Bob write a read command Alice
    Then the user Bob can view the timeline of Alice

  Scenario: Charlie can subscribe to Alice’s timelines
    Given that the user/users Charlie and Alice is/are using console-based social networking application
    When the user Charlie write a command Charlie follows Alice to follow the user Alice
    Then the user Charlie subscribe to the timelines of Alice

  Scenario: Charlie can view an aggregated list of all subscriptions of Alice
    Given that the user/users Charlie is/are using console-based social networking application
    When the user Charlie write a command Charlie wall to see all subscriptions of Alice
    Then the user Charlie can view an aggregated list of all subscriptions of Alice
    