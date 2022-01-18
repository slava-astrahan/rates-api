package ratesapi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ratesapi.clients.GiphyClient;
import ratesapi.entities.dto.giphy.GifData;
import ratesapi.entities.dto.giphy.GitImage;
import ratesapi.entities.dto.giphy.OriginalImage;

@Service
@RequiredArgsConstructor
public class GetGifService {

    private static final String SEARCH_BROKE_GIF_WORD = "broke";
    private static final String SEARCH_RICH_GIF_WORD = "rich";
    private static final String URL_REGEX = "https://media\\d";

    private final GiphyClient giphyClient;

    @Value("${giphy.api_key}")
    private String apiKey;

    public String getBrokeGif() {
        return getGif(SEARCH_BROKE_GIF_WORD);
    }

    public String getRichGif() {
        return getGif(SEARCH_RICH_GIF_WORD);
    }

    private String getGif(String searchWord) {
        return giphyClient.getGif(apiKey, searchWord, 1).getData()
                .stream()
                .map(GifData::getImages)
                .map(GitImage::getOriginal)
                .map(OriginalImage::getUrl)
                .map(this::changeURL)
                .findAny()
                .orElseThrow();
    }

    private String changeURL(String url) {
       return url.replaceFirst(URL_REGEX, "https://i");
    }
}
