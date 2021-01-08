import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private static final String SANTA_INSTRUCTIONS = "Advent of code/2015/day1/src/main/resources/instructionsForSanta.txt";

    public static void main(String[] args) {

        File file = new File(SANTA_INSTRUCTIONS);
        whichFloor(file);
        findBasement(file);
    }

    private static void findBasement(File file) {
        int indexOfBasement;
        List<Integer> floors = travelOfSanta(file);
        indexOfBasement = floors.indexOf(-1) + 1;
        System.out.println("Index of basement: " + indexOfBasement);
    }

    private static void whichFloor(File file) {
        List<Integer> floors = travelOfSanta(file);
        int floor = floors.get(floors.size() - 1);
        System.out.println("Floor for Santa: " + floor);
    }

    private static List<Integer> travelOfSanta(File file) {
        int floor = 0;
        List<Integer> floors = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String instructions = reader.readLine();
            for (char i : instructions.toCharArray()) {
                if (i == '(') {
                    floor += 1;
                } else if (i == ')') {
                    floor -= 1;
                }
                floors.add(floor);
            }
            return floors;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return floors;
    }
}
