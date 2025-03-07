package dev.zymixon.inventory_service.exceptions;

public class InventoryItemNotFoundException extends RuntimeException{
    public InventoryItemNotFoundException(String message) {
        super(message);
    }

}
