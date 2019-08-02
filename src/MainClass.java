import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Random;

public class MainClass {
    public static void main(String[] args) {
//        String dirName = System.getProperty("java.io.tmpdir");
        String dirName = "C:\\Temp";
        final int quantityOfFiles = 20;
        final double maxSize = 1.5 * 1e6;
//        final double maxSize = 1e9;
        for (int i = 1; i <= quantityOfFiles; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    File file = File.createTempFile(String.format("%s_%d", LocalDate.now().toString(), finalI), ".txt", new File(dirName));
                    try (FileWriter fileWriter = new FileWriter(file)) {
                        PrintWriter printWriter = new PrintWriter(fileWriter);
                        while (file.length() <= maxSize) {
                            for (int j = 0; j < 100; j++) {
                                printWriter.print(new Random().nextInt(10000));
                                printWriter.print(", ");
                            }
                            printWriter.print("\n");
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }).start();
        }
    }
}
