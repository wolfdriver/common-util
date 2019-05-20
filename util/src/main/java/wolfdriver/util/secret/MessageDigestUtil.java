package wolfdriver.util.secret;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

public class MessageDigestUtil {

    private final static String UTF8 = "utf-8";


    public static byte[] md5(String content) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        return digest.digest(content.getBytes(UTF8));
    }


    public static byte[] sha(String content) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        return digest.digest(content.getBytes(UTF8));
    }


    public static String byte2Hex(byte[] bytes) {
        StringBuilder sBuilder = new StringBuilder();
        for (int i=0; i<bytes.length; i++) {
            byte b = bytes[i];
            boolean negative = false;   // 是否为负数
            if (b < 0) {
                negative = true;
            }
            int intE = Math.abs(b);
            if (negative) {
                intE = intE | 0x80;
            }
            String tmp = Integer.toHexString(intE & 0xFF);
            if (tmp.length() == 1) {
                sBuilder.append("0");
            }
            sBuilder.append(tmp.toLowerCase());
        }
        return sBuilder.toString();
    }

    public static byte[] hex2Byte(String hex) {
        byte[] bytes = new byte[hex.length()/2];
        for (int i=0; i<hex.length(); i = i + 2) {
            String subStr = hex.substring(i, i+2);
            boolean negative = false;
            int intE = Integer.parseInt(subStr, 16);
            if (intE > 127) {
                negative = true;
            }
            if (intE == 128) {
                intE = -128;
            } else if(negative) {
                intE = 0 - (intE & 0x7F);
            }
            byte b = (byte)intE;
            bytes[i/2] = b;
        }
        return bytes;
    }



    public static String byte2Base64(byte[] bytes) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(bytes);
    }

    public static byte[] base642Byte(String base64Content) throws Exception{
        BASE64Decoder base64Decoder = new BASE64Decoder();
        return base64Decoder.decodeBuffer(base64Content);
    }



    public static void main(String[] args) throws Exception{
        System.out.println(byte2Hex("AEFZ12".getBytes()));
    }

}
