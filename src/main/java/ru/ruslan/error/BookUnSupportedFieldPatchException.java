package ru.ruslan.error;

public class BookUnSupportedFieldPatchException extends RuntimeException {

    public BookUnSupportedFieldPatchException(String keys) {
        super("Field " + keys.toString() + " update is not allow.");
    }

}