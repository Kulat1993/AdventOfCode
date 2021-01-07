import java.io.*;

public class Application {

    private static final String SANTA_INSTRUCTIONS = "src/main/resources/instructionsForSanta.txt";

    public static void main(String[] args) {

        File file = new File(SANTA_INSTRUCTIONS);
        whichFloor(file);
        findBasement(file);
    }

    private static void findBasement(File file) {
        int floor = 0;
        int index = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String instructions = reader.readLine();
            int chars = instructions.toCharArray().length;
            for (char i : instructions.toCharArray()) {
                index++;
                if (i == '(') {
                    floor += 1;
                } else if (i == ')') {
                    floor -= 1;
                }
                if (floor == -1) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Position of character: " + index);
        }
    }

    private static void whichFloor(File file) {
        int floor = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String instructions = reader.readLine();
            for (char i : instructions.toCharArray()) {
                if (i == '(') {
                    floor += 1;
                } else if (i == ')') {
                    floor -= 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Floor for Santa: " + floor);
        }
    }
}
