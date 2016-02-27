package token;

/**
 * The <code>TokenUtil</code> ...
 * 
 * @author gibranecastillo
 *
 */
public class TokenUtil {
	//TODO Planning to use this class for security
	
	/*public static final String MAGIC_KEY = "obfuscate";
	
	public static String createToken(UserDetails userDetails) {
		long expires = System.currentTimeMillis() + 1000L * 60 * 60;
		
		StringBuilder tokenBuilder = new StringBuilder();
		tokenBuilder.append(userDetails.getUsername());
		tokenBuilder.append(":");
		tokenBuilder.append(expires);
		tokenBuilder.append(":");
		tokenBuilder.append(TokenUtil.computeSignature(userDetails, expires));
		
		return tokenBuilder.toString();
	}
	
	public static String computeSignature(UserDetails userDetails, long expires) {
		StringBuilder signatureBuilder = new StringBuilder();
		signatureBuilder.append(userDetails.getUsername());
		signatureBuilder.append(":");
		signatureBuilder.append(expires);
		signatureBuilder.append(":");
		signatureBuilder.append(userDetails.getPassword());
		signatureBuilder.append(":");
		signatureBuilder.append(TokenUtil.MAGIC_KEY);
		
		MessageDigest digest;
		
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch(NoSuchAlgorithmException e) {
			throw new IllegalStateException("No MD5 algorithm available!");
		}
		
		return new String(Hex.encode(digest.digest(signatureBuilder.toString().getBytes())));
	}
	
	public static String getUserNameFromToken(String authToken) {
		if(null == authToken) {
			return null;
		}
		
		String[] parts = authToken.split(":");
		
		return parts[0];
	}
	
	public static boolean validateToken(String authToken, UserDetails userDetails) {
		String[] parts = authToken.split(":");
		long expires = Long.parseLong(parts[1]);
		String signature = parts[2];
		
		if(expires < System.currentTimeMillis()) {
			return false;
		}
		
		return signature.equals(TokenUtil.computeSignature(userDetails, expires));
	}*/
}