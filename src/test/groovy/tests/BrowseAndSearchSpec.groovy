package tests

import geb.spock.GebReportingSpec
import modules.ProductCard
import pages.Main
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class BrowseAndSearchSpec extends GebReportingSpec {

    @Shared String searchItem = 'blouse'

    void setupSpec() {
        given: ''
        to Main
    }

    void 'Add and remove items from the shopping cart'() {
        when: 'Select Women category'
        waitFor { at Main }
        womenTab.click()

        then: 'Verify category is open, all subcategories are present and product items are displayed'
        waitFor { categoryName == 'WOMEN' }
        assert subCategories[0].text() == 'TOPS'
        assert subCategories[1].text() == 'DRESSES'
        assert products.size() > 0

        when: 'Select Dresses category'
        dressesTab.click()

        then: 'Verify category is open, all subcategories are present and product items are displayed'
        waitFor { categoryName == 'DRESSES' }
        assert subCategories[0].text() == 'CASUAL DRESSES'
        assert subCategories[1].text() == 'EVENING DRESSES'
        assert subCategories[2].text() == 'SUMMER DRESSES'
        assert products.size() > 0

        when: 'Select T-Shirts category'
        tshirtsTab('T-shirts').click()

        then: 'Verify that category is open, product items displayed'
        waitFor { categoryName == 'T-SHIRTS' }
        assert products.size() > 0
    }

    void 'Search for one item and verify all products displayed have search query in the name'() {
        when: 'Search for blouse'
        waitFor { at Main }
        searchBar.value(searchItem)
        searchIcon.click()

        then: 'Verify that all products displayed have search query in the name'
        waitFor { pageHeading.contains(searchItem.toUpperCase()) }
        products.each { ProductCard product ->
            assert product.name.toUpperCase().contains(searchItem.toUpperCase())
        }

    }
}
