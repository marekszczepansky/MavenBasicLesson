package task.crc32;

import java.io.*;
import java.util.Scanner;
import java.util.zip.CRC32;

public class BasicApp {

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);
        System.out.print("Source file name: ");
        String sourceFilename = reader.nextLine();
        System.out.print("Target file name: ");
        String targetFilename = reader.nextLine();
        System.out.print("Chunk size: ");
        int chunkSize = reader.nextInt();

        splitFileWithCRC32(sourceFilename, targetFilename, chunkSize);
        readFileWithCRC32(targetFilename, chunkSize);

    }

    private static void readFileWithCRC32(String targetFilename, int chunkSize) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(targetFilename)));

        byte[] buffer = new byte[chunkSize];
        int read;
        do {
            long readCrc32 = in.readLong();
            CRC32 crc32 = new CRC32();
            read = in.read(buffer, 0, chunkSize);
            crc32.update(buffer, 0, read);
            long crc32Value = crc32.getValue();
            if (readCrc32 == crc32Value) {
                System.out.write(buffer, 0, read);
            }
        } while (read == chunkSize);
        in.close();
    }

    private static void splitFileWithCRC32(String sourceFilename, String targetFilename, int chunkSize) throws IOException {
        File original = new File(sourceFilename);
        long originalLength = original.length();
        byte[] buffer = new byte[chunkSize];

        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(original)));
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(targetFilename)));

        for (int sourcePosition = 0; sourcePosition <= originalLength; sourcePosition += chunkSize) {

            CRC32 crc32 = new CRC32();
            int read = in.read(buffer, 0, chunkSize);
            crc32.update(buffer, 0, read);
            long chunkCrc32 = crc32.getValue();
            out.writeLong(chunkCrc32);
            out.write(buffer, 0, read);
        }
        in.close();
        out.close();
    }
}
