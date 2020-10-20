package crud.security.handler;

import crud.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {

        User user = (User)authentication.getPrincipal();

        if (user.getRoles().stream().anyMatch(role -> role.getName().equals("admin"))){
            httpServletResponse.sendRedirect("/admin");
        }else {
            httpServletResponse.sendRedirect("/user?");
        }
    }
}
