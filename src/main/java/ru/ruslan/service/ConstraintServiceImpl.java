package ru.ruslan.service;

import org.springframework.stereotype.Component;
import ru.ruslan.service.contract.ConstraintService;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service for errors processing
 */
@Component
public class ConstraintServiceImpl implements ConstraintService {

    @Override
    public Map<String, String> getConstraintErrors(ConstraintViolationException constraintViolationException) {
        return constraintViolationException.getConstraintViolations()
                .stream().collect(
                        Collectors.toMap(ConstraintViolation::getMessageTemplate, ConstraintViolation::getMessage)
                );
    }

}