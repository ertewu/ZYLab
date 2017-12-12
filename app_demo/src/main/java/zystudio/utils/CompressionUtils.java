package zystudio.utils;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


public class CompressionUtils {

    public static byte[] compress(byte[] data) throws IOException {
        if (ByteUtil.isEmpty(data)) {
            return ByteUtil.EMPTY_BYTES;
        }
        Deflater deflater = new Deflater(Deflater.DEFAULT_COMPRESSION, true);
        deflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        deflater.finish();
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer); // returns the generated code... index
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
        deflater.end();
        byte[] output = outputStream.toByteArray();
        return output;
    }

    public static byte[] decompress(byte[] data) throws IOException, DataFormatException {
        if (ByteUtil.isEmpty(data)) {
            return ByteUtil.EMPTY_BYTES;
        }
        Inflater inflater = new Inflater(true);
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!inflater.finished()) {
            int count = inflater.inflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
        inflater.end();
        byte[] output = outputStream.toByteArray();
        return output;
    }

}
