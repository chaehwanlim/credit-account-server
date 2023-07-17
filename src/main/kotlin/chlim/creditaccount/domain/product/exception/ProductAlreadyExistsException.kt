package chlim.creditaccount.domain.product.exception

import chlim.creditaccount.domain.store.Store

private const val MESSAGE = "Product already exists."

class ProductAlreadyExistsException(
    store: Store,
    name: String
): RuntimeException(
    MESSAGE + "storeName=${store.name}, name=$name"
)
