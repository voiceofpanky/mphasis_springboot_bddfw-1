Feature: Home page

	@web @home_page
	Scenario Outline: Check page display
		Given A user navigates to HomePage "<countryCode>"
		Then Google logo is displayed
		And search bar is displayed

		Examples:
			| countryCode |
			| fr          |
			| com         |

	@web @home_page
	Scenario: Check title
		Given A user navigates to HomePage "fr"
		Then page title is "Google"
