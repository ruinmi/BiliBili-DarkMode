import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;

/**
 * Created by elio on 16/05/2023
 */
public class Generator {

    static String activityPrefix = "@-moz-document url-prefix(\"https://t.bilibili.com\") {";
    static String genericPrefix = "@-moz-document domain(\"bilibili.com\") {";

    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(new File("style.css").toPath())));
        File file = new File(Generator.class.getClassLoader().getResource("").getFile());
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.getName().contains(".css")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Generator.class.getClassLoader().getResourceAsStream(f.getName())), StandardCharsets.UTF_8));
                if (f.getName().contains("03-")) {
                    writer.write(activityPrefix);
                } else {
                    writer.write(genericPrefix);
                }
                writer.newLine();
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                }
                writer.write("}");
                writer.newLine();
                reader.close();
            }
        }
        writer.close();
    }
}
