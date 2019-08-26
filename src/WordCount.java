
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.StringTokenizer;

public class WordCount {

    public static void main(String[] args) throws IOException {

        //Creating BufferedReader to accept the file name from the user
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName = null;
        int maxValue=0;

        System.out.println("Нужно ввести путь к файлу.");
        System.out.println("Можно ввести абсолютный путь к файлу.");
        System.out.println("Можно ввести название файла, находящегося в директории проекта. (Text.txt уже лежит в директории), также можно ввести файл из вложенной папки src/Text.txt");


        fileName = (String) br.readLine();


        if (fileName.isEmpty()) {
            System.err.println("Вы ничего не ввели");
            br.close();
        }
        else
            try {
                //Creating the BufferedReader to read the file
                Path testFilePath = Paths.get(fileName);
                File textFile = new File(String.valueOf(testFilePath.toAbsolutePath()));
                BufferedReader input = new BufferedReader(new FileReader(textFile));

                //Creating the Map to store the words and their occurrences
                TreeMap<String, Integer> frequencyMap = new TreeMap<String, Integer>();
                String currentLine = null;

                //Reading line by line from the text file
                while ((currentLine = input.readLine()) != null) {

                    currentLine= currentLine.replaceAll("[^a-zA-Zа-яёА-ЯЁ]+", " ");
                    StringTokenizer parser = new StringTokenizer(currentLine, " \t\n\r\f.,;–:-+!?'\\u2014\\u2013\\u2012()1234567890\\s+");
                    while (parser.hasMoreTokens()) {
                        String currentWord = parser.nextToken().toLowerCase();
                        Integer frequency = frequencyMap.get(currentWord);
                        if (frequency == null) {
                            frequency = 0;
                        }
                        //Putting each word and its occurrence into Map
                        frequencyMap.put(currentWord, frequency + 1);
                    }

                }

                for (String key : frequencyMap.keySet()) {
                    System.out.print(key + " ");
                }
                System.out.println(" ");
                System.out.println(frequencyMap);
                maxValue = Collections.max(frequencyMap.values());

                System.out.println("Максимально встречаются (частота " + maxValue + ") следующие слова: ");
                for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
                    if (entry.getValue().equals(maxValue)) {
                        System.out.println(entry.getKey());
                    }
                }

            }

            catch (FileNotFoundException e){
                System.err.println("Файл не найден!");
            }

            catch (IOException ie) {
                ie.printStackTrace();
            }
            finally {
                    br.close();
            }
    }
}



