package tests

import geb.spock.GebReportingSpec
import pages.Authentication
import pages.Base
import pages.Main
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class CreateAccountSpec extends GebReportingSpec {

    @Shared String randomNumber = String.valueOf( (Math.random() * 10000) as int )
    @Shared String firstName = 'John'
    @Shared String lastName = 'Doe'
    @Shared String email = 'abc' + randomNumber + '@gmail.com'
    @Shared String password = 'qwerty'

    void setupSpec() {
        given: ''
        to Main
    }

    void 'Create new Account'() {
        when: 'Navigate to Authentication page'
        waitFor { at Main }
        signIn.click()

        then: 'Authentication page is displayed'
        waitFor { at Authentication }

        when: 'Enter the email address, click on Create an account and fill out all of the required fields'
        createBasicAccount(firstName, lastName, email, password)

        then: 'Verify that My Account page is displayed and logout'
        waitFor { $('h1', text: 'MY ACCOUNT').isDisplayed() }
        signOut.click()
    }

    void 'Log back in with email and password'() {
        when: 'Enter the email address and the password for newly created account'
        waitFor { at Authentication }
        login(email, password)

        then: 'Verify that user is logged in and its first and last name are displayed'
        waitFor { account.text() ==  firstName + " " + lastName}
    }

    void cleanupSpec() {
        page Base
        signOut.click()
    }


}
