package com.he1extg.converterui.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SpringSecurityConfiguration {

    @Bean
    fun getSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            csrf {
                disable()
            }
            anonymous {
                principal = "guest"
            }
            authorizeRequests {
                authorize("/", "/**", permitAll)
            }
            formLogin {
                loginPage = "/login"
                defaultSuccessUrl("/", true)
            }
        }
        return http.build()
    }
}