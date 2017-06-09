Feature: I want to be able to book flights and check if my actions were successful

  Scenario: user logs in and checks sum for flight to be equal
    Given User is on main page
    And User signs in
    When User enters "tatester@12storage.com" in login field and "qwerty12345" in password field
    And User finds flight with data from file
    Then User checks sum for flight to be equal

  Scenario: user goes to the timetable page and checks flight price equals to the price in the seaarch page
    Given User is on main page
    And User opens timetable
    When User enters "Vilnius" in origin field and "Tel-Aviv" in destination field
    And User gets on search page and searches for flight
    Then User asserts that flight price in timetable and flight price in search page are equal

  Scenario: user searches for origin and destination in the map
    Given User is on main page
    And User opens map
    When User chooses route
    Then User goes to timetable to check if map search was successful

  Scenario: user logs in and fills the passenger data field
    Given User is on main page
    And User signs in
    When User enters "tatester@12storage.com" in login field and "qwerty12345" in password field
    And User finds flight
      |origin|destination|depDate|retDate|passenger|numberOfPassengers|
      |Vilnius|Tel-Aviv  |22       |29   |NO       |0                |
    And User picks exact flights
    And User fills the fields with the expected passenger information from file
    And User gets actual passenger data
    Then User compares expected passenger data with actual passenger data


  Scenario: User books flight with wrong card (cause we don't want to book a real flight)
    Given User is on main page
    And User signs in
    When User enters "tatester@12storage.com" in login field and "qwerty12345" in password field
    And User finds flight
      |origin|destination|depDate|retDate|passenger|numberOfPassengers|
      |Vilnius|Tel-Aviv  |22       |29   |NO       |0                |
    And User picks exact flights
    And User fills the fields with the expected passenger information from file
    And User fills baggage
    And User goes to seat selection page
    And User selects seat
    And User returns to seat selection
    And User selects seat
    And User submits selection
    And User declines insurance offer
    And User passes on services
    And User declines discount offer
    And User fills personal billing details
    And User fills credit card data
    And User accepts the privacy policy
    Then User gets message of rejection

  Scenario: User compares flight price with infant to flight price without infant
    Given User is on main page
    When User finds flight with data from file
    And User picks exact flights
    And User gets price for current flight
    Then User returns to main page
    And User finds flight with infant
    And User picks exact flights
    And User get price for flight with infant
    Then User compares flight price with infant to flight price without infant

  Scenario: I don't want to be able to book a flight with a wrong return date
    Given User is on main page
    And User opens timetable
    When User enters "Vilnius" in origin field and "Tel-Aviv" in destination field and "06" in month field
    And User picks exact departure flight
    And User picks wrong return flight
    Then User checks if button is disabled

  Scenario: User checks if flight price is equal regardless of wether he is logged in or off
    Given User is on main page
    When User finds flight with data from file
    And User picks exact flights
    And User gets price for current flight
    Then User returns to main page
    And User signs in
    And User enters "tatester@12storage.com" in login field and "qwerty12345" in password field
    And User finds flight with data from file
    And User picks exact flights
    And User gets price for current flight after signing in
    Then User compares prices before and after signing in


