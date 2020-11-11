package tests

import geb.spock.GebReportingSpec
import modules.ProductCard
import pages.Authentication
import pages.Base
import pages.Order
import pages.Main
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class CheckoutSpec extends GebReportingSpec {

    @Shared String productName
    @Shared String price
    @Shared String randomNumber = String.valueOf( (Math.random() * 10000) as int )
    @Shared String email = 'abc' + randomNumber + '@gmail.com'
    @Shared String password = 'qwerty'
    @Shared Map<String, String> address = [
            firstName: 'New',
            lastName: 'Customer',
            address: '1000 Main St',
            city: 'San Francisco',
            state: 'California',
            zipCode: '90000',
            country: 'United States',
            phone: '123 456 7890'
    ]
    @Shared Map<String, String> userDetails = address + [email: email, password: password]

    void setupSpec() {
        given: ''
        to Main
    }

    void 'Add one item to the shopping cart and proceed to checkout'() {
        when: 'Select Dresses category'
        waitFor { at Main }
        tshirtsTab.click()

        then: 'Category is displayed'
        waitFor { categoryName == 'T-SHIRTS' }

        when: 'Find first available product, record the item name and add it to cart'
        ProductCard availableProduct = getFirstAvailableProduct()
        productName = availableProduct.name
        interact { moveToElement(availableProduct) }
        availableProduct.button('Add to cart').click()

        then: 'Product was added successfully'
        waitFor { $('h2', text: contains('Product successfully added to your shopping cart')) }

        when: 'Click on Proceed to checkout'
        button('Proceed to checkout').click()

        then: 'Shopping cart opens, verify name of the product that was added'
        waitFor { at Order }
        assert productName == cartItems[0].productName

        when: 'Record the total price including shipping and proceed to checkout'
        price = totalPrice
        button('Proceed to checkout').click()

        then: 'Authentication page is displayed'
        waitFor { at Authentication }
    }

    void 'Create new account, enter all required information and verify the shippind address'() {
        when: 'Enter the email address, click on Create an account and fill out all of the required fields'
        waitFor { at Authentication }
        createAccount(userDetails)

        then: 'Verify the shipping and billing addresses contain correct information from the account'
        waitFor { at Order }
        waitFor { pageHeading == 'ADDRESSES' }
        address.each { String key, String value ->
            assert deliveryAddress.contains(value)
            assert invoiceAddress.contains(value)
        }
    }

    void 'Proceed to Shipping section, agree with terms of services'() {
        when: 'Proceed to Shipping'
        waitFor { at Order }
        button('Proceed to checkout').click()

        then: 'Shipping details are displayed'
        waitFor { pageHeading == 'SHIPPING' }

        when: 'Accept terms of services'
        termsOfServiceCheckbox.click()

        then: 'Proceed to Payment'
        button('Proceed to checkout').click()
    }

    void 'Proceed to Payment'() {
        when: 'At Payment'
        waitFor { at Order }
        waitFor { pageHeading.contains('PAYMENT METHOD') }

        then: 'Verify that item name and total price are correct'
        assert productName == cartItems[0].productName
        assert price == totalPrice

        when: 'Select payment by bank wire'
        bankwirePayment.click()

        then: 'Wait for Order Summary to appear'
        waitFor { pageHeading == 'ORDER SUMMARY' }

        when: 'Confirm the Order'
        button('I confirm my order').click()

        then: 'Verify that order confirmation page is displayed and shopping cart is empty'
        waitFor { pageHeading == ('ORDER CONFIRMATION') }
        assert cartIsEmpty()
    }

    void cleanupSpec() {
        page Base
        signOut.click()
    }
}
