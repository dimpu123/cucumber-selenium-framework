Feature: Login to the orange HRM site
	
	@TagOne
	Scenario: User able to login HRM and able to see dashboard page
	Given User is on login page
	When User enter UserName and Password
	And Click on login button
	Then User navigate to dashboard page
	
	@TagTwo
	Scenario: User able to login HRM and able to see dashboard page
	Given User is on login page
	When User enter UserName and Password
	And Click on login button
	Then click on PIM tab 
	And Enter the PIM details 


  @TagSix
  Scenario: User able to test bureau veritas application or not 
  Given User is in application page
  When User enters details 
  And  click submit button
  Then close the browser 
	