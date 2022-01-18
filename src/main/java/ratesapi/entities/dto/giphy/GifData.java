package ratesapi.entities.dto.giphy;

import lombok.Data;

@Data
public class GifData {
    private String type;
    private GitImage images;
}
