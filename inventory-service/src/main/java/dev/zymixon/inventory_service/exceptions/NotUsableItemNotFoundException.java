package dev.zymixon.inventory_service.exceptions;

public class NotUsableItemNotFoundException extends RuntimeException {

    public NotUsableItemNotFoundException(String message) {
        super(message);
    }

}
