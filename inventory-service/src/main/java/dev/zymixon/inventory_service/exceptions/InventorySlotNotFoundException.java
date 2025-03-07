package dev.zymixon.inventory_service.exceptions;

public class InventorySlotNotFoundException extends RuntimeException {
    public InventorySlotNotFoundException(String message) {
        super(message);
    }
}
