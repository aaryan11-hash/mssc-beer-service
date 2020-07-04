package srivastava.aaryan.msscbeerservice.web.mappers;

import org.mapstruct.Mapper;
import srivastava.aaryan.msscbeerservice.domain.Beer;
import srivastava.aaryan.msscbeerservice.web.model.BeerDto;

@Mapper(uses = {DateMapperHandler.class})
public interface BeerMapper {

BeerDto BeerToBeerDto(Beer beer);

Beer BeerDtoToBeer(BeerDto beerDto);
}
