package com.origin.library.infrastructure.controller;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.origin.library.domain.User;
import com.origin.library.domain.error.UserNotFoundError;

public class RequestUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(User.class) &&
                parameter.hasParameterAnnotation(RequestUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
            NativeWebRequest request, WebDataBinderFactory factory) throws Exception {

        Object value = request.getAttribute(UserHandlerInterceptor.userAttr, NativeWebRequest.SCOPE_REQUEST);
        if (value == null) {
            throw new UserNotFoundError().setDetails("find user from http request by @RequestUser");
        }
        return (User) value;
    }
}