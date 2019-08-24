import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.EnumMap;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

public class QRDecode {
    private static String decode(File qrCodeImage)
            throws IOException, NotFoundException, ChecksumException,
                   FormatException {

        Map<DecodeHintType, Boolean> hints
            = new EnumMap<DecodeHintType, Boolean> (DecodeHintType.class);
        hints.put(DecodeHintType.TRY_HARDER, true);

        return new QRCodeReader()
            .decode(
                new BinaryBitmap(
                    new HybridBinarizer(
                        new BufferedImageLuminanceSource(
                            ImageIO.read(qrCodeImage)
                        )
                    )
                ),
                hints
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
            System.err.println("Not found: " + e.getMessage());
        }
        catch (ChecksumException e) {
            System.err.println("Error correction failed: " + e.getMessage());
        }
        catch (FormatException e) {
            System.err.println("Decoding failed: " + e.getMessage());
        }
        catch (Exception e) {
            System.err.println("Unknown Error: " + e.getMessage());
        }
    }
}
