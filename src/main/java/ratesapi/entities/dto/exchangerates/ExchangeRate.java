package ratesapi.entities.dto.exchangerates;

import java.util.Map;
import lombok.Data;

@Data
public class ExchangeRate {
    private String base;
    private Map<String, Double> rates;
}
