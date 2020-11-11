package modules

import geb.Module

class CartItem extends Module {

    static cls = 'cart_item'

    static content = {
        productName { $('.product-name').text().trim() }
        removeIcon { $(class: 'cart_quantity_delete') }
    }
}
