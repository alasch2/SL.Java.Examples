package io.sl.ex.utils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {

	/**
	 * 
	 * @param fileToRead
	 * @return
	 * @throws IOException
	 */
	public static byte[] readFileToBytes(File fileToRead) {
		try {
			return readData(new FileInputStream(fileToRead));
		} 
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Read the stream into byte array
	 * @param inputStream
	 * @return
	 * @throws java.io.IOException
	 */
    public static byte[] readData(InputStream inputStream) {
        try {
			return readDataNice(inputStream);
		} 
        finally {
        	close(inputStream);
		}
    }

    public static byte[] readDataNice(InputStream inputStream) {
		ByteArrayOutputStream boTemp = null;
        byte[] buffer = null;
        try {
            int read;
			buffer = new byte[8192];
            boTemp = new ByteArrayOutputStream();
            while ((read=inputStream.read(buffer, 0, 8192)) > -1) {
                boTemp.write(buffer, 0, read);
            }
            return boTemp.toByteArray();
        } catch (IOException e) {
			throw new RuntimeException(e);
        }
	}
	
    /**
     * Close streams (in or out)
     * @param stream
     */
    public static void close(Closeable stream) {
        if (stream != null) {
            try {
                if (stream instanceof Flushable) {
                    ((Flushable)stream).flush();
                }
                stream.close();
            } catch (IOException e) {
                // When the stream is closed or interupted, can ignore this exception
            }
        }
    }
}