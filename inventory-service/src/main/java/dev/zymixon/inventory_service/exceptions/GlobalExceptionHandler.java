package dev.zymixon.inventory_service.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(CharacterEquipmentNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleCharacterInventoryNotFoundException(CharacterEquipmentNotFoundException ex) {
        logger.warn("Character equipment not found: {}", ex.getMessage());
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotUsableItemNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotUsableNotFoundException(NotUsableItemNotFoundException ex) {
        logger.warn("Not usable item not found: {}", ex.getMessage());
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InventoryItemNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleInventoryItemNotFoundException(InventoryItemNotFoundException ex) {
        logger.warn("Inventory item not found: {}", ex.getMessage());
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InventorySlotNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleInventorySlotNotFoundException(InventorySlotNotFoundException ex) {
        logger.warn("Inventory slot not found: {}", ex.getMessage());
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItemTemplateNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleItemTemplateNotFoundException(ItemTemplateNotFoundException ex) {
        logger.warn("Item template not found: {}", ex.getMessage());
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CharacterInventoryNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleCharacterInventoryNotFoundException(CharacterInventoryNotFoundException ex) {
        logger.warn("Character inventory not found: {}", ex.getMessage());
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        logger.error("Unexpected error: {}", ex.getMessage(), ex);
        return buildErrorResponse("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message, HttpStatus status) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", status.value());
        errorResponse.put("error", status.getReasonPhrase());
        errorResponse.put("message", message);

        return new ResponseEntity<>(errorResponse, status);
    }

}
