package com.exchanges.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

public class Average {
    public String Average(String code, String date) throws Exception {
        String url = "http://api.nbp.pl/api/exchangerates/rates/A/" + code + "/" + date + "/";
        RestTemplate restTemplate = new RestTemplate();
        ExchangeRate exchangeRate = null;
        try {
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            exchangeRate = mapper.readValue(response, ExchangeRate.class);
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return "Non-working day or wrong datatype";
        } catch (NullPointerException e) {
            return "Null pointer error";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while fetching data from API.";
        }
        if (exchangeRate == null) {
            return "Null pointer error";
        }
        return "average value: " + exchangeRate.getRates()[0].getMid();
    }
}
