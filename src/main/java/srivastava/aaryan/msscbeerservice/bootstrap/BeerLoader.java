package srivastava.aaryan.msscbeerservice.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import srivastava.aaryan.msscbeerservice.domain.Beer;
import srivastava.aaryan.msscbeerservice.repositories.BeerRepository;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private BeerRepository beerRepository;

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
                    .upc(33701000001L)
                    .price(new BigDecimal("12.56"))
            .build());




        }
        System.out.println("Loaded Beers"+beerRepository.count());

    }
}
