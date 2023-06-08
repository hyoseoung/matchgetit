package matchgetit.backend.Util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;

public class TokenValidator {
    private final CsrfTokenRepository tokenRepository;

    public TokenValidator(CsrfTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public boolean validateCsrfToken(String token, HttpServletRequest request) {
        CsrfToken storedToken = tokenRepository.loadToken(request);
        if (storedToken != null && token.equals(storedToken.getToken())) {
            return true; // 토큰이 유효한 경우
        } else {
            return false; // 토큰이 유효하지 않은 경우
        }
    }
}
