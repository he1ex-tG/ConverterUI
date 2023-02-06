package com.he1extg.converterui.controller

import com.he1extg.converterui.security.User
import com.he1extg.converterui.security.UserDetailsServiceImpl
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class AuthenticationController(
    private val passwordEncoder: PasswordEncoder,
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
        userDetailsService.addUser(User(username, passwordEncoder.encode(password)))
        redirectAttributes.addAttribute("username", username)
        return "redirect:/login"
    }
}