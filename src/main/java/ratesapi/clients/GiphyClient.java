package ratesapi.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ratesapi.entities.dto.giphy.GiphyResponse;

@FeignClient(url = "${giphy.url}", name = "giphy-client")
public interface GiphyClient {

    @GetMapping
    GiphyResponse getGif(@RequestParam(name = "api_key") String key,
                         @RequestParam(name = "q") String searchWord,
                         @RequestParam(name = "limit") int limit);
}
