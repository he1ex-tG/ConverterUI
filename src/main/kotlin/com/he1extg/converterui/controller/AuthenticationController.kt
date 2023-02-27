package com.he1extg.converterui.controller

import com.he1extg.converterui.model.UserRegistration
import com.he1extg.converterui.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.validation.Valid

@Controller
class AuthenticationController(
    private val userService: UserService
) {

    @GetMapping("/login")
    fun getLogin(): String {
        return "login"
    }

    @GetMapping("/registration")
    fun getRegistration(model: Model): String {
        model.addAttribute(UserRegistration())
        return "registration"
    }

    @PostMapping("/registration")
    fun postRegistration(
        @Valid
        userRegistration: UserRegistration,
        redirectAttributes: RedirectAttributes
    ): String {
        val userDTO = userService.addUser(userRegistration.username, userRegistration.password)
        redirectAttributes.addAttribute("username", userDTO.username)
        return "redirect:/login"
    }
}