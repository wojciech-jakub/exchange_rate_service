package com.example.ExchangeRateService.controller;
import com.example.ExchangeRateService.service.CurrencyService;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyExchangeControllerTest {
    @Autowired
    private MockMvc mvc;


    @Test
    public void getCountedRequestedCurrencies() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .get("/countedRequests")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString().isEmpty();
    }

    @Test
    public void convertCurrencies() throws Exception {
        JSONObject postValue = new JSONObject();
        postValue.put("fromCurrency", "USD");
        postValue.put("toCurrency", "USD");
        postValue.put("amount", 120);

        MvcResult storyResult = mvc.perform(MockMvcRequestBuilders
                .post("/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(postValue.toJSONString()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }
    @Test
    public void getCurrencies() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/currencies")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
    @Test
    public void shouldGet400() throws Exception {
        JSONObject postValue = new JSONObject();
        postValue.put("fromCurrency", "asdasdasdasd");
        postValue.put("toCurrency", "USD");
        postValue.put("amount", 120);

        MvcResult storyResult = mvc.perform(MockMvcRequestBuilders
                .post("/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(postValue.toJSONString()))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
    }

}

