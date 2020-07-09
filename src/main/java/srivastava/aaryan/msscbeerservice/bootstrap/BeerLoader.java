package srivastava.aaryan.msscbeerservice.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import srivastava.aaryan.msscbeerservice.domain.Beer;
import srivastava.aaryan.msscbeerservice.repositories.BeerRepository;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC="0631234200036";
    public static final String BEER_2_UPC="0631234300019";
    public static final String BEER_3_UPC="0003783375213";
    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository){
        this.beerRepository=beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects(){
        if(beerRepository.count()==0){

            beerRepository.save(Beer.builder()
            .beerName("mongo bobs")
                    .beerStyle("IPA")
                    .minOnHand(10)
            .quantityToBrew(200)
                    .upc(BEER_1_UPC)
                    .price(new BigDecimal("12.56"))
            .build());

            beerRepository.save(Beer.builder()
            .beerName("budweiser")
            .beerStyle("IPA")
            .quantityToBrew(300)
            .price(new BigDecimal("34.90"))
            .upc(BEER_2_UPC).build());

        }

        System.out.println("Loaded Beers"+beerRepository.count());

    }
}
