package com.acorngaru.konggaru.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class StringToObjectResolver implements HandlerMethodArgumentResolver {
    private final ObjectMapper objectMapper;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        log.info("supportsParameter() - {}", methodParameter.hasParameterAnnotation(StringToObject.class));
        return methodParameter.hasParameterAnnotation(StringToObject.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        StringToObject annotation = methodParameter.getParameterAnnotation(StringToObject.class);

        assert annotation != null;

        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();

        log.info("resolveArguent() - name: {}", annotation.name());

        String stringifiedParam = request.getParameter(annotation.name());

        log.info("{}", stringifiedParam);

        return objectMapper.readValue(stringifiedParam, methodParameter.getParameterType());
    }
}
