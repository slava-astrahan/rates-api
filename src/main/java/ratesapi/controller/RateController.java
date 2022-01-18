package ratesapi.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ratesapi.services.RateService;

import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;

@RequiredArgsConstructor
@RestController
public class RateController {

    private final RateService rateService;

    @GetMapping(value = "/rate/{curr}", produces = IMAGE_GIF_VALUE)
    public byte[] getRateGif(@PathVariable(name = "curr") String currency) throws IOException {
        String gifUrl = rateService.gefGigDependsOnExchangeRate(currency);
        URL url = new URL(gifUrl);
        BufferedImage image = ImageIO.read(url);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "gif", os);
        return os.toByteArray();
    }
}
