package ru.ruslan.error;

import java.util.Set;

public class BookUnSupportedFieldPatchException extends RuntimeException {

    public BookUnSupportedFieldPatchException(String keys) {
        super("Field " + keys.toString() + " update is not allow.");
    }

}