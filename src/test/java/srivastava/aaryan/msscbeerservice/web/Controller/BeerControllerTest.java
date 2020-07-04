package srivastava.aaryan.msscbeerservice.web.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import srivastava.aaryan.msscbeerservice.web.model.BeerDto;
import srivastava.aaryan.msscbeerservice.web.model.BeerStyleEnum;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer/"+ UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto=getBeerDto();

        String beerDtoJson=objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(post("/api/v1/beer/").contentType(MediaType.APPLICATION_JSON)
        .content(beerDtoJson)).andExpect(status().isCreated());
    }

    @Test
    void updateById() throws Exception {
        BeerDto beerDto=getBeerDto();
        String beerDtoJson=objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/"+UUID.randomUUID().toString()).contentType(MediaType.APPLICATION_JSON)
        .content(beerDtoJson)).andExpect(status().isNoContent());
    }

    BeerDto getBeerDto(){

        return BeerDto.builder().beerName("Budweiser").beerStyle(BeerStyleEnum.ALE).upc(1112233L).price(new BigDecimal("12.567")).build();
    }
}