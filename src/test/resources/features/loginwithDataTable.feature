Feature: Login to the orange HRM site
	
	@Tagfour
	Scenario: User able to login HRM and able to see dashboard page using data table 
	Given User is on login page of HRM
	When User enters UserName and Password
	|UserName|Password|
	|Admin   |admin123|
  And  Click on the login button
	Then User navigate to dashbaord of HRM
	
	
	
	
	@Tagfive
		
	Scenario: User able to login HRM and able to see dashboard page using data table 
	Given User is on login page of HRM
	When User enters UserName and Password
	|UserName|Password|
	|Admin   |admin123|
  And  Click on the login button
  Then User navigate to PIM tab
  Then Enter PIM tab1 details
  |EmployeeName|EmployeeId|SupervisorName|EmployeeStatus    |Include            | Job Title      |SubUnit|
  |SeleniumJava|123456    |Sai Santhosh  |Full-Time Contract|Past Employees Only|HR Manager      |Administration|
  