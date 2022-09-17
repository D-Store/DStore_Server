package gg.dstore.admin.config;

import gg.dstore.domain.entity.UserEntity;
import gg.dstore.enums.Role;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class AccessControlInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserEntity user = (UserEntity) request.getAttribute("user");
        if (!user.getRole().equals(Role.ADMIN)) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "권한 없음");
        }
        return true;
    }
}


