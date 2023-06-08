package matchgetit.backend.Config;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers(getRequestMatcher("/matchGetIt/login/**")).permitAll()
                .requestMatchers(getRequestMatcher("/matchGetIt/logout")).permitAll() // Allow logout request
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(characterEncodingFilter(), CsrfFilter.class)
                .csrf(csrf -> {
                    csrf.csrfTokenRepository(csrfTokenRepository());
                    csrf.requireCsrfProtectionMatcher(csrfProtectionMatcher());
                });

        return http.build();
    }

    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        tokenRepository.setCookiePath("/");
        return tokenRepository;
    }

    private RequestMatcher getRequestMatcher(String path) {
        return new AntPathRequestMatcher(path);
    }

    private RequestMatcher csrfProtectionMatcher() {
        return new RequestMatcher() {
            private final AntPathRequestMatcher[] requestMatchers = {
                    new AntPathRequestMatcher("/matchGetIt/login/**"),
                    new AntPathRequestMatcher("/matchGetIt/logout") // Add logout URL pattern
            };

            @Override
            public boolean matches(HttpServletRequest request) {
                for (AntPathRequestMatcher matcher : requestMatchers) {
                    if (matcher.matches(request)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }
}
