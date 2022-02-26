package com.hesssoftware.gravie.challange.controllers

import com.hesssoftware.gravie.challange.models.CartObject
import com.hesssoftware.gravie.challange.models.GameResult
import com.hesssoftware.gravie.challange.repositories.CartObjectRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import java.util.*
import kotlin.collections.HashMap

@Controller
class CartController(val cartObjectRepository: CartObjectRepository) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @RequestMapping("/cart/")
    fun home(model: Model): String {
        val cartObjects = cartObjectRepository.findAll()
        model.addAttribute("cartObjects", cartObjects)
        return "cart"
    }

    @RequestMapping("/cart/add")
    fun add(model: ModelMap, @RequestParam itemId: String, @RequestParam name: String): ModelAndView {
        logger.info("Received Add request.")
        cartObjectRepository.save(CartObject(UUID.randomUUID(), UUID.randomUUID(), GameResult(itemId, name, null, HashMap())))

        return ModelAndView("redirect:/cart/", model)
    }

    @RequestMapping("/cart/remove")
    fun remove(model: ModelMap, @RequestParam itemId: UUID): ModelAndView {
        logger.info("Received Add request.")
        cartObjectRepository.deleteById(itemId)
        return ModelAndView("redirect:/cart/", model)
    }
}