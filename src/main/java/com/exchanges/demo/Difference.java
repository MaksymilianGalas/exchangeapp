package com.exchanges.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

public class Difference {

    public String Difference(String code, String N) throws Exception  {
        try {
            int days;
            try {
                days = Integer.parseInt(N);
            } catch (NumberFormatException e) {
                return "Wrong data type";
            }
            if (days > 255) {
                return "Only 255 days back are allowed";
            }
            LocalDate date = LocalDate.now();
            String codeFromQuotation = null;
            double min = Double.MAX_VALUE; // minimal difference between buy and ask
            double max = Double.MIN_VALUE; // maximal difference between buy and ask
            String response = " ";
            for (int i = 0; i < days; i++) {

                String url = "http://api.nbp.pl/api/exchangerates/rates/C/" + code + "/" + date + "/";
                RestTemplate restTemplate = new RestTemplate();

                try {
                    response = restTemplate.getForObject(url, String.class);
                    if (response == null) {
                        return "null pointer error";
                    }
                    ObjectMapper mapper = new ObjectMapper();
                    Quotation quotation = mapper.readValue(response, Quotation.class);
                    double diff = (quotation.getRates()[0].getBid() - quotation.getRates()[0].getAsk()); // diff - difference in rates
                    double absDiff = Math.abs(diff); // absolute value of diff
                    codeFromQuotation = quotation.getCode();
                    if (absDiff <= min) {
                        min = absDiff;
                    }
                    if (absDiff >= max) {
                        max = absDiff;
                    }
                } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
                    // if API returns 404 error, skip this day (because of holidays etc.)
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Error occurred while fetching data from API.";
                }

                date = date.minusDays(1);
            }
            if (codeFromQuotation == null) {
                return "wrong datatype";
            } else if (!code.equals(codeFromQuotation)) {
                return "wrong datatype";
            }
            return "Min difference: " + String.format("%.5f", min) + "\nMax difference: " + String.format("%.5f", max);
        } catch (Exception e){
            return "Error occurred while fetching data from API.";
        }
    }
}
