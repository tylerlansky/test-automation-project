package pages

import modules.CartItem

class Order extends Base {

    static at = { title == "Order - My Store" }

    static content = {
        cartItems { $(class: CartItem.cls).moduleList(CartItem) }
        alertMessage { $('.alert-warning').text().trim() }
        deliveryAddress { $('#address_delivery').text() }
        invoiceAddress { $('#address_invoice').text() }
        termsOfServiceCheckbox { $('.checkbox', text: contains('terms of service')).$('input') }
        bankwirePayment { $('.payment_module').$('.bankwire')}
        checkPayment { $('.payment_module').$('.cheque')}
        totalPrice { $('#total_price').text().trim() }
    }

}
