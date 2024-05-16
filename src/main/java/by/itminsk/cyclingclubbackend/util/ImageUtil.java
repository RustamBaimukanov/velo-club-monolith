package by.itminsk.cyclingclubbackend.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageUtil {
    public static byte[] compressAndEncodeImage(MultipartFile multipartFile) throws IOException {
        byte[] resultArray = multipartFile.getBytes();
        if (multipartFile.getSize() > 400000) {
            try {
                ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
                Thumbnails.of(new ByteArrayInputStream(multipartFile.getBytes())).scale(0.5).outputFormat("jpg").toOutputStream(resultStream);
                resultArray = resultStream.toByteArray();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return resultArray;
    }
}
