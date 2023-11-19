package com.riat.inventory.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.riat.inventory.bean.Item;
import com.riat.inventory.dto.ItemDto;
import com.riat.inventory.repository.ItemsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ItemService {

    private final ItemsRepository itemsRepository;
    private final ObjectMapper objectMapper;

    public ItemService(ItemsRepository itemsRepository, ObjectMapper objectMapper) {
        this.itemsRepository = itemsRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public void addNewItems(ItemDto itemDto){
        var item = itemsRepository.getItemByName(itemDto.getName());
        if(item.isPresent()){
            item.get().setCount(item.get().getCount() + itemDto.getCount());
            itemsRepository.save(item.get());
        }
        else{
            Item newItem = objectMapper.convertValue(itemDto, Item.class);
            itemsRepository.save(newItem);
        }
    }

    public List<ItemDto> getItems(){
        return itemsRepository.findAll().stream().map(this::convertItemToDto).collect(Collectors.toList());
    }

    private ItemDto convertItemToDto(Item item){
        ItemDto itemDto = new ItemDto();
        itemDto.setCount(item.getCount());
        itemDto.setName(item.getName());
        itemDto.setDescription(item.getDescription());
        itemDto.setPrice(item.getPrice());
        return itemDto;
    }

}
