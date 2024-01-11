package com.corso.ticketrain.treno.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;

import javax.sql.rowset.serial.SerialBlob;

import org.hibernate.engine.jdbc.BlobProxy;

public class BlobConverter {
	
	 public static Blob generateBlob(String filePath) throws Exception {
	        try (InputStream is = new FileInputStream(filePath);
	             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

	            byte[] buffer = new byte[1024];
	            int byteLetti;

	            while ((byteLetti = is.read(buffer, 0, buffer.length)) != -1) {
	                baos.write(buffer, 0, byteLetti);
	            }

	            baos.flush();
	            byte[] data = baos.toByteArray();

	            // Usa javax.sql.rowset.serial.SerialBlob invece di BlobProxy
	            Blob result = new SerialBlob(data);
	            return result;
	        }
	    }
	
	public static void saveFile(Blob blob,String filePath) throws Exception {
		InputStream is = blob.getBinaryStream();
    	FileOutputStream fos = new FileOutputStream(filePath);
		byte [] buffer = new byte[1024];
		int read = -1;
		while ( (read = is.read(buffer, 0, buffer.length)) != -1)  {
			fos.write(buffer, 0, read);
		}
		fos.flush();
		fos.close();
	}
}
