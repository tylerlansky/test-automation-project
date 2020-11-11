package pages

import geb.Page

class Base extends Page {

    static content = {
        signIn { $('.header_user_info').$('.login') }
        signOut { $('.header_user_info').$('.logout') }
        account { $('.header_user_info').$('.account') }
        shoppingCart { $('.shopping_cart').$(title: 'View my shopping cart') }
        searchBar { $('.search_query') }
        searchIcon { $(name: 'submit_search') }
        pageHeading { $('.page-heading').text().trim() }
        button { String label -> $(class: 'btn', text: label) }
        textField { String label -> $('label', text: contains(label)).parent('.form-group').$('input') }
        selectField { String label -> $('label', text: contains(label)).parent('.form-group').$('select') }
        selectOption { String name -> $('option', text: name) }
    }

    boolean cartIsEmpty() {
        shoppingCart.text().contains('(empty)')
    }
}
