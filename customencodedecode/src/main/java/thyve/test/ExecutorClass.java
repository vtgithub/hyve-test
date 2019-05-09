package thyve.test;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import thyve.test.service.CustomDecoder;
import thyve.test.service.CustomEncoder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExecutorClass {

    private static int BUFFER_SIZE=10; //10B
    private static int USE_TRIVIAL_IMPLEMENTATION = 0;
    final static Logger logger = Logger.getLogger(ExecutorClass.class);

    public static void main(String[] args) throws IOException {
        try {
            String inputPath = args[0];
            String outputPath = args[1];
            InputStream fileInputStream = new FileInputStream(inputPath);
            OutputStream fileOutputStream = new FileOutputStream(outputPath);
            System.out.println("please enter your key:\n encoding: 1\n decoding: 2");
            Scanner scanner = new Scanner(System.in);
            String encodeOrDecode = scanner.nextLine();
            List<Byte> resultList = new ArrayList<Byte>();
            if (Integer.parseInt(encodeOrDecode) == 2){
                System.out.println("decoding ..");
                CustomDecoder customDecoder = new CustomDecoder();
                byte[] buffer = new byte[BUFFER_SIZE];
                while (fileInputStream.read(buffer) != -1) { // reading from binary file
                    customDecoder.batchDecode(resultList,buffer); // decode buffered size of bytes
                }
                fileOutputStream.write(ArrayUtils.toPrimitive(resultList.toArray(new Byte[resultList.size()]))); // write to binary file
            }else if (Integer.parseInt(encodeOrDecode) == 1){
                System.out.println("please enter your encoding style:\n trivial: 1\n non-trivial: 0");
                Scanner encoderScanner = new Scanner(System.in);
                USE_TRIVIAL_IMPLEMENTATION = Integer.parseInt(encoderScanner.nextLine());
                CustomEncoder customEncoder = new CustomEncoder();
                byte[] buffer = new byte[BUFFER_SIZE];
                while (fileInputStream.read(buffer) != -1) { // reading from binary file
                    customEncoder.batchEncode(resultList,buffer, USE_TRIVIAL_IMPLEMENTATION); // decode buffered size of bytes
                }
                fileOutputStream.write(ArrayUtils.toPrimitive(resultList.toArray(new Byte[resultList.size()])));
                System.out.println("process ended please check output file -> " + outputPath);
            }

        }catch (Exception e){
            logger.error(e);
        }

    }
}
