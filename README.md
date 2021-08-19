# Spring Boot + Selenium + BDD

## Requirements:

* JDK 8
* Maven

## Building the project:

* Download or clone the repository
* Open a terminal
* From the project root directory run: mvn install

## Enabling Lombok in Eclipse in case of errors related to Getter methods:

* Open command prompt and navigate to lombok jar - .m2\repository\org\projectlombok\lombok\1.18.12
* Execute following cmd - java -jar lombok-1.18.12.jar
* Click "Specify Location" button in the launched Lombok Installer window and locate eclipse.exe path under eclipse
  installation folder
* Click Install/Update
* Quit Installer

## Test Execution:

* Run CucumberRunner.java as JUnit Test
* Set -Dcucumber.publish.quiet=true in VM Configration to set the publish not displayed on Console.

## Using WebDriverManager:

* Update local browser version in application-qa.yml file before using WebDriverManager

## Gerkin-Lint

### Pre-Requisites

* npm install -g gherkin-lint
* Navigate to the root folder of the project
* chmod +x gherkin_beautify.sh

### Linting rules

* Rules being configured under {root}/lint/rule_lint.json

### Execution

* ./gherkin_beautify.sh

### Parallel Execution on different web browsers
* enable below property in your config yml file
* Run testng.xml suiteFile or build pom.xml
* parallel.crossbrowser :true
