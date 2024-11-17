package seg3x02.tempconverterapi

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

  @Bean
  @Throws(Exception::class)
  fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
    http
            .authorizeHttpRequests { auth -> auth.anyRequest().authenticated() }.httpBasic(Customizer.withDefaults())
    return http.build()
  }

  @Bean
  fun userDetailsService(): UserDetailsService {
    val users = InMemoryUserDetailsManager()
    users.createUser(User.withDefaultPasswordEncoder().username("user1").password("pass1").build())
    users.createUser(User.withDefaultPasswordEncoder().username("user2").password("pass2").build())

    return users
  }

}
