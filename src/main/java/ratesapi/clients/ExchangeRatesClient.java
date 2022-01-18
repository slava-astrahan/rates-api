package ratesapi.clients;

import java.time.LocalDate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ratesapi.entities.dto.exchangerates.ExchangeRate;

@FeignClient(url = "${exchange_rates.url}", name = "exchange-rates-client")
public interface ExchangeRatesClient {

    // https://openexchangerates.org/api/latest.json?app_id=YOUR_APP_ID&base=GBP
    @GetMapping(path = "/latest.json")
    ExchangeRate getLatestRates(@RequestParam(name = "app_id") String appId,
                                @RequestParam(name = "base") String baseCurrency);

    // https://openexchangerates.org/api/historical/2022-01-14.json?app_id=c01f180784e94065ab742d1971b80b59&base=USD
    @GetMapping(path = "/historical/{date}.json")
    ExchangeRate getRatesByDate(@PathVariable(name = "date")
                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                @RequestParam(name = "app_id") String appId,
                                @RequestParam(name = "base") String baseCurrency);
}
