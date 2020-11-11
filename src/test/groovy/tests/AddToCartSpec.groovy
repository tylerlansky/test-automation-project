package tests

import geb.spock.GebReportingSpec
import modules.ProductCard
import pages.Order
import pages.Main
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class AddToCartSpec extends GebReportingSpec {

    @Shared productName

    void setupSpec() {
        given: ''
        to Main
    }

    void 'Add and remove items from the shopping cart'() {
        when: 'Select Dresses category'
        waitFor { at Main }
        dressesTab.click()

        then: 'Category is displayed'
        waitFor { categoryName == 'DRESSES' }

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

        when: 'Click on trash bin icon to remove product from the cart'
        cartItems[0].removeIcon.click()

        then: 'Verify that message is displayed saying cart is empty'
        waitFor { alertMessage == 'Your shopping cart is empty.'}
    }
}
