package dev.zymixon.inventory_service.exceptions;

public class CharacterEquipmentNotFoundException extends RuntimeException {
    public CharacterEquipmentNotFoundException(String message) {
        super(message);
    }
}
