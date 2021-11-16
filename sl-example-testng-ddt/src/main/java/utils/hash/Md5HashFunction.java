package utils.hash;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5HashFunction implements HashFunction{
    @Override
    public String getHash(String payload) {
        return DigestUtils.md5Hex(payload);
    }
}
