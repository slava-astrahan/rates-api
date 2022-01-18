package ratesapi.entities.dto.giphy;

import java.util.List;
import lombok.Data;

@Data
public class GiphyResponse {
    private List<GifData> data;
}
