## Test Automation Project


### Description

This is test automation project uses Geb, Spock and Gradle.
The build is setup to work with Chrome browser. Have a look at the `build.gradle` and the `src/test/resources/GebConfig.groovy` files for configuration details.

### Usage

The following command will launch all tests in the Chrome browser:

    ./gradlew chromeTest
   
To run a specific test use --tests flag and provide fully qualified test name (with the package):

    ./gradlew chromeTest --tests tests.AddToCartSpec

Replace `./gradlew` with `gradlew.bat` in the above examples if you're on Windows.

### Requirements

Java Version 8 or higher should be installed on the machine. No additional installation is required.

### Report

The example of the Test Execution Report can be found in the report directory. Just open the index.html file. After each test execution the new report gets generated in the build/reports/tests/chromeTest folder.

## Conclusion

####QA and Testing
Obviously there are still a lot of functionality that should be tested. 
I focused my automated tests on the "happy path" scenarios that cover most import aspects of e-commerce website:
adding items to cart, checkout, create account and login, search for items and browse categories.
If I had more time I would cover the remaining options and deviations in checkout process, account creation, etc.
Also I would have worked on additional site features like "Share Product on Social Media"

####Code and Framework
The framework can be improved in different areas. The Order (check out) page can be represented by modules where each module would cover one step of checkout process (cart, shipping, etc.).
Only elements that I worked with in the 4 tests are described, obviously there are many more elements remained.
All classes except for tests don't have any comments, and although the functions and variables have self explanatory names, the comments should be added and code structure should be improved.   

