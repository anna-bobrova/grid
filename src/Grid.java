import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Grid {
    public static void main(String[] args) throws Exception {
        File fileJDF = new File("D:\\Text\\myJob.jdf");
        fileJDF.createNewFile();
        FileWriter JdfWriter = new FileWriter(fileJDF);
        JdfWriter.append("job:\n");
        JdfWriter.append("\tname: sum\n");
        JdfWriter.append("\tinit:\n");
        JdfWriter.append("\t\tput text1.txt text2.txt\n");
        JdfWriter.append("\t\tput Text.class Text.class\n");
        Scanner sc = new Scanner(new File("D:\\Text\\text1.txt"));

        String text1 = new String(Files.readAllBytes(Paths.get("D:\\Text\\text1.txt")), "UTF-8");
        String text2 = new String(Files.readAllBytes(Paths.get("D:\\Text\\text2.txt")), "UTF-8");

        int taskCount;

        for (taskCount = 0; taskCount < 5; taskCount++) {
            //sc.nextLine();
            JdfWriter.append("task: remote: java grid.Sum " + taskCount + "\n");
            JdfWriter.append("\t  final: get out" + taskCount + ".txt out" + taskCount + ".txt\n");
        }

        JdfWriter.close();
        //произошла и jdf запихнулся в грид, началось выполнение
        //ждем когда он нам вернет файлы результатов
        int sum = 0;
        HashSet<String> common = new HashSet<>();
        for (int i = 0; i < taskCount; i++) {
            File out = new File("out" + i + ".txt");
            while (!out.exists()) Thread.sleep(100);
            Scanner scOut = new Scanner(out);
            while (scOut.hasNextLine()) {
                String s = scOut.nextLine();
                common.add(s);
            }
        }

        List<String> commonStrings = new ArrayList<>();
        commonStrings.addAll(common);

        for (int i = 0; i < commonStrings.size()-1; i++) {
            for (int j = 0; i < commonStrings.size()-1; j++) {
                if (j == commonStrings.size()) {
                    break;
                }
                if (i != j && commonStrings.get(i).contains(commonStrings.get(j))) {
                    commonStrings.remove(j);
                    j--;
                }
            }
        }

        for (String str : commonStrings) {
            System.out.println(str);
        }
    }
}
