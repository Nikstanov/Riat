package com.riat.inventory.repository;

import com.riat.inventory.bean.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Long> {

    public Optional<Item> getItemByName(String name);
}
