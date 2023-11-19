package com.riat.inventory.controller;

import com.riat.inventory.config.SecurityConfig;
import com.riat.inventory.dto.ItemDto;
import com.riat.inventory.service.ItemService;
import com.riat.inventory.service.KafkaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
public class MainController {

    private final ItemService itemService;
    private  final KafkaService kafkaService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid ItemDto item, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            kafkaService.log("Inventory service - invalid values");
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.getName());
        log.info(authentication.toString());
        var jwt = (Jwt)authentication.getPrincipal();
        String name = (String) jwt.getClaims().get("preferred_username");
        itemService.addNewItems(item);
        kafkaService.log("Inventory service - new items by user " + name +" "+item);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<ItemDto>> get() {
        return new ResponseEntity<>(itemService.getItems(), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<HttpStatus> error(Exception e){
        log.error(e.getMessage());
        kafkaService.error("Inventory service - " + e.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }
}
