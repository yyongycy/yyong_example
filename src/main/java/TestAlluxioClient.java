import alluxio.AlluxioURI;
import alluxio.client.file.FileInStream;
import alluxio.client.file.FileSystem;
import alluxio.exception.AlluxioException;

import java.io.IOException;

public class TestAlluxioClient {
    public static int BUFFER_SIZE = 100;

    public static void main(String[] args) throws IOException, AlluxioException {
        if (args.length <= 1) {
            System.out.println("Usage: java -cp");
            System.exit(1);
           return;
        }

        FileSystem fs = alluxio.client.file.FileSystem.Factory.get();
        String filePathArg = args[args.length - 1];
        //String filePathArg = "/test.txt";
        AlluxioURI path = new AlluxioURI(filePathArg);

        System.out.printf("Reading the leading %d bytes of file %s\n\n", BUFFER_SIZE, filePathArg);

        byte[] buffer = new byte[BUFFER_SIZE];
        try (FileInStream inStream = fs.openFile(path)) {
            while (inStream.read(buffer) > 0) {
                System.out.println(new String(buffer));
                break;
            }
        }
    }
}
