import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class QRDecode {
    private static String decode(File qrCodeImage)
            throws IOException, NotFoundException {
        return new MultiFormatReader()
            .decode(
                new BinaryBitmap(
                    new HybridBinarizer(
                        new BufferedImageLuminanceSource(
                            ImageIO.read(qrCodeImage)
                        )
                    )
                )
            ).getText();
    }

    public static void main(String args[]) {
        if (args.length != 1) {
            System.err.println("Usage:\n\tjava -jar qrdecode.jar <filename>\n");
            System.exit(1);
        }

        try {
            System.out.println(decode(new File(args[0])));
        }
        catch (IOException e) {
            System.err.println("Could not read file: " + e.getMessage());
        }
        catch (NotFoundException e) {
            System.err.println("Could not decode QR: " + e.getMessage());
        }
        catch (Exception e) {
            System.err.println("Unknown Error: " + e.getMessage());
        }
    }
}
