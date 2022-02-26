package com.hesssoftware.gravie.challange.repositories

import com.hesssoftware.gravie.challange.models.CartObject
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.UUID

interface CartObjectRepository: MongoRepository<CartObject, UUID>