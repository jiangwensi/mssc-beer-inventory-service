package guru.springframework.common.events;

import lombok.NoArgsConstructor;

/**
 * Created by Jiang Wensi on 8/11/2020
 */
@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {
    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
