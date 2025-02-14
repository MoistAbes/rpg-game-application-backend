//package dev.zymixon.user_service.client;
//
//import dev.zymixon.user_service.dto.MyCharacterDto;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.List;
//
//@FeignClient(name = "character-service", url = "http://character-service")
//public interface CharacterClient {
//
//    @GetMapping("/characters/get-all-by-user/{userId}") // Match controller path
//    List<MyCharacterDto> getCharactersByUserId(@PathVariable Long userId);
//
//    @PostMapping("/create/{name}/{userId}")
//    MyCharacterDto createCharacter(@PathVariable String name, @PathVariable Long userId);
//
//}
