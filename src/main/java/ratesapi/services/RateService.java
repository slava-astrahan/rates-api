package ratesapi.services;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RateService {

    private final ExchangeRatesService exchangeRatesService;
    private final GetGifService getGifService;

    public String gefGigDependsOnExchangeRate(String currency) {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        double lastExchangeRate = exchangeRatesService.getLastExchangeRate(currency);
        double yesterdaysExchangeRate = exchangeRatesService.getExchangeRate(currency, yesterday);
        if (lastExchangeRate > yesterdaysExchangeRate) {
            return getGifService.getRichGif();
        }
        return getGifService.getBrokeGif();
    }
}
