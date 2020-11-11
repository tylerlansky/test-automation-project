package pages


import modules.ProductCard

class Main extends Base {

    static at = { title.contains("My Store") }

    static content = {
        womenTab { $('a', href: contains('controller=category'), title: 'Women') }
        dressesTab {  $('.menu-content').$(title: 'Dresses')[1]  }
        tshirtsTab { $('.menu-content').$(title: 'T-shirts')[1] }
        categoryName { $('.cat-name').text().trim() }
        subCategories { $('.subcategory-name') }
        products { $(class: ProductCard.cls).moduleList(ProductCard) }
    }

    ProductCard getFirstAvailableProduct() {
        waitFor { products }
        products.find { ProductCard product -> product.isAvailable() }
    }


}
