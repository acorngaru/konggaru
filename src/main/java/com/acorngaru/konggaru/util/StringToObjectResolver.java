package com.acorngaru.konggaru.util;

import com.acorngaru.konggaru.exception.InvalidValueException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Setter
@Component
@RequiredArgsConstructor
public class StringToObjectResolver implements HandlerMethodArgumentResolver {
    private final ObjectMapper objectMapper;
    private final LocalValidatorFactoryBean validator;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
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
        String stringifiedParam = request.getParameter(annotation.name());
        Object object = objectMapper.readValue(stringifiedParam, methodParameter.getParameterType());

        BindingResult bindingResult = new BeanPropertyBindingResult(object, "object");
        validator.validate(object, bindingResult);
        if (bindingResult.hasErrors())
            throw new InvalidValueException(bindingResult);

        return object;
    }
}
