package dev.zymixon.inventory_service.exceptions;

public class CharacterInventoryNotFoundException extends RuntimeException {
    public CharacterInventoryNotFoundException(String message) {
        super(message);
    }
}
