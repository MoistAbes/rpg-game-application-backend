package dev.zymixon.inventory_service.exceptions;

public class ItemTemplateNotFoundException extends RuntimeException{
    public ItemTemplateNotFoundException(String message) {
        super(message);
    }

}
