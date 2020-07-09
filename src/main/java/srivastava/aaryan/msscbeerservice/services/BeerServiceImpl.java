package srivastava.aaryan.msscbeerservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import srivastava.aaryan.msscbeerservice.domain.Beer;
import srivastava.aaryan.msscbeerservice.repositories.BeerRepository;
import srivastava.aaryan.msscbeerservice.web.Controller.NotFoundException;
import srivastava.aaryan.msscbeerservice.web.mappers.BeerMapper;
import srivastava.aaryan.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {


    private final BeerRepository beerRepository;

    private final BeerMapper beerMapper;


    @Override
    public BeerDto getById(UUID beerId) {
        return beerMapper.beerToBeerDto(
                beerRepository.findById(beerId).orElseThrow(NotFoundException::new)
        );
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer=beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());
        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }
}
