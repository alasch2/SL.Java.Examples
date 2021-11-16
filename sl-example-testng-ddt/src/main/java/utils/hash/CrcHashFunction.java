package utils.hash;

import utils.hash.HashFunction;

import java.util.zip.CRC32;

public class CrcHashFunction implements HashFunction {
    @Override
    public String getHash(String payload) {
        CRC32 crc32 = new CRC32();
        crc32.update(payload.getBytes());
        long value = crc32.getValue();
        return Long.toHexString(value);
    }
}
