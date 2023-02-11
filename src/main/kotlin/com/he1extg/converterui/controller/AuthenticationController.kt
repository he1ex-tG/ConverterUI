package com.he1extg.converterui.controller

import com.he1extg.converterui.security.UserDetailsImpl
import com.he1extg.converterui.security.UserDetailsServiceImpl
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class AuthenticationController(
    private val userDetailsService: UserDetailsServiceImpl
) {

    @GetMapping("/login")
    fun getLogin(): String {
        return "login"
    }

    @GetMapping("/registration")
    fun getRegistration(): String {
        return "registration"
    }

    @PostMapping("/registration")
    fun postRegistration(
        username: String,
        password: String,
        redirectAttributes: RedirectAttributes
    ): String {
        userDetailsService.addUser(UserDetailsImpl(username, password))
        redirectAttributes.addAttribute("username", username)
        return "redirect:/login"
    }
}