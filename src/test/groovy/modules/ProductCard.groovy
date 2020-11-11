package modules

import geb.Module

class ProductCard extends Module {

    static cls = 'product-container'

    static content = {
        name { $('.product-name').text().trim() }
        button { String label -> $(class: 'btn', text: label) }
        availability { $('.availability').text().trim() }
    }

    boolean isAvailable() {
        availability == 'In stock'
    }
}
