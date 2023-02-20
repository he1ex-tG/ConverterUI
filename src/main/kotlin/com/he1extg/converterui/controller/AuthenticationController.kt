package com.he1extg.converterui.controller

import com.he1extg.converterui.dto.user.NewUserDTO
import com.he1extg.converterui.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class AuthenticationController(
    private val userService: UserService
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
        val userDTO = userService.addUser {
            NewUserDTO(username, password)
        }
        redirectAttributes.addAttribute("username", userDTO.username)
        return "redirect:/login"
    }
}