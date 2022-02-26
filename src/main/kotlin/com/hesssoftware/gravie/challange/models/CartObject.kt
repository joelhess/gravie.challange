package com.hesssoftware.gravie.challange.models

import java.util.UUID

data class CartObject(
    val id: UUID,
    val userId: UUID,
    val item: Any,
)
