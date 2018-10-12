package fragmadata.soluction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 * @author Sony
 * this class contains all utility method related to any file
 */
public class FileUtil {
    //comma delimiter
    public static final String COMMA_DELIMITER = ",";

    //read the file and retun BufferReader
    public static BufferedReader getBufferReader(String fileName){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch(FileNotFoundException e) {
            System.out.println(fileName+" not found");
        }
        return reader;
    }
}
