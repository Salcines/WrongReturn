import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String dataset = "Dataset/dataset.txt";
        System.out.println("File loaded");

        try {
            String elements = readFile(dataset);

            Map<Integer, Integer> repetitions = new HashMap<Integer, Integer>();

            for (char numbers : elements.toCharArray()) {
                if (Character.isDigit(numbers)) {
                    int number = Character.getNumericValue(numbers);
                    repetitions.put (number, repetitions.getOrDefault(number, 0) + 1);
                }
            }

            List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(repetitions.entrySet());

            list.sort((first, second) ->{
                int comparate = second.getValue().compareTo(first.getValue());
                if (comparate == 0) {
                    return second.getKey().compareTo(first.getKey());
                }

                return comparate;
            });


            for (int i = 0; i < Math.min(4, list.size()); i++) {
                Map.Entry<Integer, Integer> e = list.get(i);
                System.out.printf("Number: %d ", e.getKey());
            }


        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

    }

    private static String readFile(String dataset) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(dataset))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString();
    }
}
