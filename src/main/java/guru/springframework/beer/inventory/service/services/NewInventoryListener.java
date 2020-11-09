package guru.springframework.beer.inventory.service.services;

import guru.springframework.beer.inventory.service.config.JMSConfig;
import guru.springframework.beer.inventory.service.domain.BeerInventory;
import guru.sfg.brewery.model.events.NewInventoryEvent;
import guru.springframework.beer.inventory.service.repositories.BeerInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Jiang Wensi on 8/11/2020
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class NewInventoryListener {
    private final BeerInventoryRepository beerInventoryRepository;

    @Transactional
    @JmsListener(destination = JMSConfig.NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent event) {
        log.debug("Got Inventory: "+event.toString());
        beerInventoryRepository.save(BeerInventory.builder()
                .beerId(event.getBeerDto().getId())
                .upc(event.getBeerDto().getUpc())
                .quantityOnHand(event.getBeerDto().getQuantityOnHand())
                .build());
    }
}
