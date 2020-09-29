package ru.ruslan.service;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.util.Map;

/**
 * The interface Constraint service.
 */
public interface ConstraintService {
    Map<String, String> getConstraintErrors(ConstraintViolationException constraintViolationException);

}