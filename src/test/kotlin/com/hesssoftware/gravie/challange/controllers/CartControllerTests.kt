package com.hesssoftware.gravie.challange.controllers

import com.hesssoftware.gravie.challange.models.CartObject
import com.hesssoftware.gravie.challange.repositories.CartObjectRepository

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import org.springframework.ui.ModelMap
import java.util.UUID

class CartControllerTests: FreeSpec() {

    init {
        val cartObjectRepository = mockk<CartObjectRepository>()
        val cartController = CartController(cartObjectRepository)

        "Add Test" {
            every { cartObjectRepository.save(any()) } returnsArgument 0
            val model = mockk<ModelMap>(relaxed = true)

            val result = cartController.add(model, "item", "name")

            verify {
                cartObjectRepository.save(any<CartObject>())
            }

            result.viewName.shouldBe("redirect:/cart/")
            result.model.shouldBe(model)
        }

        "Delete Test" {
            every { cartObjectRepository.deleteById(any()) } just runs
            val model = mockk<ModelMap>(relaxed = true)
            val itemId = UUID.randomUUID()

            val result = cartController.remove(model, itemId)

            verify {
                cartObjectRepository.deleteById(itemId)
            }

            result.viewName.shouldBe("redirect:/cart/")
            result.model.shouldBe(model)
        }
    }
}