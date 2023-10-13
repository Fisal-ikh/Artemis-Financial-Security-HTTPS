package com.snhu.sslserver;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}

@RestController
class ServerController{
	private static final String name = "Fisal Ikhmayes";
	private static final String HASH_ALGORITHM = "SHA-256";

	@RequestMapping("/hash")
	public String hash(){
		String checkSum = null;

		try{
			MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
			byte[] bytes = md.digest(name.getBytes(StandardCharsets.UTF_8));
			checkSum = bytesToHex(bytes);
		 } catch (NoSuchAlgorithmException e) {
            // handle the exception
            checkSum = "Error calculating.";
            e.printStackTrace();
        }
        return "<p>Data: " + name + "</p><p>Algorithm: " + HASH_ALGORITHM + "</p>CheckSum Value: " + checkSum;
	}

	private String bytesToHex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte aByte : bytes) {
            stringBuilder.append(String.format("%02x", aByte));
        }
        return stringBuilder.toString();
    }
}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";