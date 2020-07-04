package srivastava.aaryan.msscbeerservice.web.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import srivastava.aaryan.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/beer")
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId){


        //todo impl
        return new ResponseEntity<BeerDto>(BeerDto.builder().build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@Validated @RequestBody BeerDto beerDto){

        //todo impl
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateById(@PathVariable UUID beerId,@Validated @RequestBody BeerDto beerDto){

        //todo impl

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}

