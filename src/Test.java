import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        String fileName1 = "text1.txt";
        String fileName2 = "text2.txt";

        //writeFile(fileName1);
        //writeFile(fileName2);

        List<String> commonStrings = new ArrayList<>();
        commonStrings.add("Большой текст, который содержит другой текст");
        commonStrings.add("Большой текст,");
        commonStrings.add("текст");

        //commonStrings.get(0).contains(commonStrings.get(2);

        for (int i = 0; i < commonStrings.size(); i++) {
            for (int j = 0; i < commonStrings.size(); j++) {
                if (j == commonStrings.size()) {
                    break;
                }
                if (i != j && commonStrings.get(i).contains(commonStrings.get(j))) {
                    commonStrings.remove(j);
                    j--;
                }

            }

        }

        Iterator<String> it = commonStrings.iterator();


        while (it.hasNext()) {
            System.out.println(it.next() + "\n");
        }
    }


    private static String removeSpecialSymbols(String text) {
        String textOut = text.replaceAll("\\p{Cntrl}", " ");
        String textOut2 = textOut.replaceAll("  ", " ");
        return textOut2;
    }

    private static void writeFile(String fileName) throws IOException {
        File f = new File("out" + fileName);
        f.createNewFile();
        FileWriter fw = new FileWriter(f);
        String text = new String(Files.readAllBytes(Paths.get(fileName)));
        String textOut = removeSpecialSymbols(text);
        fw.append(textOut);
        fw.close();
    }


}
