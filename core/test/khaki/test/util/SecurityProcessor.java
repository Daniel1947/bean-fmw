package khaki.test.util;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SecurityProcessor {
	private static Key key = null;
	private static Cipher cipher = null;
	public static void initKey() throws Exception
	{
		byte[] raw = "0102030405060708".getBytes();
		key = new SecretKeySpec(raw, "AES");
		cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	}
	public static String encrypt(String data) throws Exception
	{
		if(null == key) initKey();
		
		//使用密钥初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, key);
		//执行操作
		return parseByte2HexStr(cipher.doFinal(data.getBytes()));
	}
	public static String decrypt(String data) throws Exception
	{
		if(null == key) initKey();
		//实例化
		//Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		//使用密钥初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, key);
		//执行操作
		return new String(cipher.doFinal(parseHexStr2Byte(data)));
	}
	
	public static void main(String[] a) throws Exception
	{
		String data = "AES数据";
		String result = encrypt(data);
		System.out.println(data);
		System.out.println(result);
		System.out.println(new String(decrypt(result)));
	}
	
    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < buf.length; i++) {
                    String hex = Integer.toHexString(buf[i] & 0xFF);
                    if (hex.length() == 1) {
                            hex = '0' + hex;
                    }
                    sb.append(hex.toUpperCase());
            }
            return sb.toString();
    }
    
    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
            if (hexStr.length() < 1)
                    return null;
            byte[] result = new byte[hexStr.length()/2];
            for (int i = 0;i< hexStr.length()/2; i++) {
                    int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
                    int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
                    result[i] = (byte) (high * 16 + low);
            }
            return result;
    }
}
