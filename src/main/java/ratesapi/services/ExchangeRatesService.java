package ratesapi.services;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ratesapi.clients.ExchangeRatesClient;

@Service
@RequiredArgsConstructor
public class ExchangeRatesService {

    private final ExchangeRatesClient ratesClient;

    @Value("${exchange_rates.app_id}")
    private String appId;

    @Value("${base_currency}")
    private String baseCurrency;

    public Double getExchangeRate(String quoteCurrency, LocalDate date) {
       return ratesClient.getRatesByDate(date, appId, baseCurrency)
               .getRates()
               .get(quoteCurrency);
    }

    public Double getLastExchangeRate(String quoteCurrency) {
        return ratesClient.getLatestRates(appId, baseCurrency)
                .getRates()
                .get(quoteCurrency);
    }
}
