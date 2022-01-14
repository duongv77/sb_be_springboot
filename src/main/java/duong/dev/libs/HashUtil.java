package duong.dev.libs;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtil {
	public static String hash(String plain) {
		return BCrypt.hashpw(plain, BCrypt.gensalt());
	}
	
	public static boolean verifile(String plain, String hashed) {
		return BCrypt.checkpw(plain, hashed);
	}
}
