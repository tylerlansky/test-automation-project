package pages

class Authentication extends Base {

    static at = { title == "Login - My Store" }

    void createBasicAccount(String firstName, String lastName, String email, String password) {
        Map<String, String> userDetails = [
                firstName: firstName,
                lastName: lastName,
                email: email,
                password: password,
                address: '101 Main St.',
                city: 'San Diego',
                state: 'California',
                zipCode: '92123',
                country: 'United States',
                phone: '123 456 7890',
        ]
        createAccount(userDetails)
    }

    void createAccount(Map<String, String> userDetails) {
        textField('Email address')[0].value(userDetails.email)
        button('Create an account').click()
        waitFor { $('h3', text: 'YOUR PERSONAL INFORMATION') }
        textField('First name').value(userDetails.firstName)
        textField('Last name').value(userDetails.lastName)
        textField('Password').value(userDetails.password)
        textField('First name')[1].value(userDetails.firstName)
        textField('Last name')[1].value(userDetails.lastName)
        textField('Address')[0].value(userDetails.address)
        textField('City').value(userDetails.city)
        selectField('State').click()
        selectOption(userDetails.state).click()
        selectField('Country').click()
        selectOption(userDetails.country).click()
        textField('Zip/Postal Code').value(userDetails.zipCode)
        textField('Mobile phone').value(userDetails.phone)
        button('Register').click()
    }

    void login(String email, String password) {
        textField('Email address')[1].value(email)
        textField('Password').value(password)
        button('Sign in').click()
    }

}
