package com.exchanges.demo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

public class MinMax {

    public String MinMax(String code, String N) {
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
            String codeFromQuotation = " ";
            double minAvg = Double.MAX_VALUE;
            double maxAvg = Double.MIN_VALUE;
            for (int i = 0; i < days; i++) {

                String url = "http://api.nbp.pl/api/exchangerates/rates/C/" + code + "/" + date + "/";
                RestTemplate restTemplate = new RestTemplate();

                try {
                    String response = restTemplate.getForObject(url, String.class);
                    ObjectMapper mapper = new ObjectMapper();
                    Quotation quotation = mapper.readValue(response, Quotation.class);
                    if (quotation == null || quotation.getRates() == null || quotation.getRates().length == 0) {
                        return "Error occurred while fetching data from API.";
                    }
                    codeFromQuotation = quotation.getCode();
                    double avg = (quotation.getRates()[0].getBid() + quotation.getRates()[0].getAsk()) / 2;
                    if (avg <= minAvg) {
                        minAvg = avg;
                    }
                    if (avg >= maxAvg) {
                        maxAvg = avg;
                    }
                } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
                    // if API returns 404 error, skip this day (because of holidays etc)
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Error occurred while fetching data from API.";
                }

                date = date.minusDays(1);
            }
            if (codeFromQuotation == null) {
                return "Error occurred while fetching data from API.";
            } else if (!code.equals(codeFromQuotation)) {
                return "Wrong currency code";
            }
            return "Min average: " + String.format("%.5f", minAvg) + "\nMax average: " + String.format("%.5f", maxAvg);
        } catch (Exception e){
            return "Error occurred while fetching data from API.";
        }
    }

}
