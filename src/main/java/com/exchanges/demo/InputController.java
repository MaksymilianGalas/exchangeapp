package com.exchanges.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InputController {
    @GetMapping("/")

    public String home(@RequestParam("date") String date, @RequestParam("code") String code, @RequestParam("num3") int num3,
                       @RequestParam("days") String days) throws Exception {
            switch (num3) {
                case 1 -> {
                    Average average = new Average();
                    return average.Average(code, date);
                }
                case 2 -> {
                    MinMax MinMax = new MinMax();
                    return MinMax.MinMax(code, days);
                }
                case 3 -> {
                    try {
                        Difference Difference = new Difference();
                        return Difference.Difference(code, days);
                    } catch (IllegalArgumentException e) {
                        return "wrong datatype";
                    }
                }
            }
            return "";

    }

}



