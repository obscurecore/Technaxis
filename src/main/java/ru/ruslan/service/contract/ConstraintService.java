package ru.ruslan.service.contract;

import javax.validation.ConstraintViolationException;
import java.util.Map;

/**
 * The interface Constraint service.
 */
public interface ConstraintService {
    Map<String, String> getConstraintErrors(ConstraintViolationException constraintViolationException);

}