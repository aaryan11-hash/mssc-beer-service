package srivastava.aaryan.msscbeerservice.web.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import srivastava.aaryan.msscbeerservice.bootstrap.BeerLoader;
import srivastava.aaryan.msscbeerservice.domain.Beer;
import srivastava.aaryan.msscbeerservice.services.BeerService;
import srivastava.aaryan.msscbeerservice.web.model.BeerDto;
import srivastava.aaryan.msscbeerservice.web.model.BeerStyleEnum;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    @Test
    void getBeerById() throws Exception {
        given(beerService.getById(any())).willReturn(getValidBeerDto());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer/"+ UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto=getBeerDto();

        String beerDtoJson=objectMapper.writeValueAsString(beerDto);

        given(beerService.saveNewBeer(any())).willReturn(getValidBeerDto());
        mockMvc.perform(post("/api/v1/beer/").contentType(MediaType.APPLICATION_JSON)
        .content(beerDtoJson)).andExpect(status().isCreated());
    }

    @Test
    void updateById() throws Exception {
       given(beerService.updateBeer(any(),any())).willReturn(getValidBeerDto());
        BeerDto beerDto=getBeerDto();
        String beerDtoJson=objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/"+UUID.randomUUID().toString()).contentType(MediaType.APPLICATION_JSON)
        .content(beerDtoJson)).andExpect(status().isNoContent());
    }

    BeerDto getBeerDto(){

        return BeerDto.builder().beerName("Budweiser").beerStyle(BeerStyleEnum.ALE).upc(BeerLoader.BEER_1_UPC).price(new BigDecimal("12.567")).build();
    }

    Beer getValidBeer(){

        return Beer.builder()
                .beerName("My Beer")
                .beerStyle("ALE")
                .price(new BigDecimal("12.5"))
                .upc(BeerLoader.BEER_1_UPC)
                .build();
    }

    BeerDto getValidBeerDto(){
        return BeerDto.builder()
                .beerName("My Beer")
                .beerStyle(BeerStyleEnum.ALE)
                .price(new BigDecimal("12.5"))
                .upc(BeerLoader.BEER_1_UPC)
                .build();
    }
}