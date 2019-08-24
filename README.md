# ZXing QRDecode

Attempting to match the impeccable performance of the now-unpublished ZXing
Android app in decoding QR Codes, but on the command line.

### Kickstart
```bash
git clone https://gitlab.com/ankitpati/qrdecode.git
cd rpg
mvn package
cp target/qrdecode-1.0-jar-with-dependencies.jar qrdecode.jar

# `qrcode.jpg` must be supplied by the user
java -jar qrdecode.jar qrcode.jpg
```
