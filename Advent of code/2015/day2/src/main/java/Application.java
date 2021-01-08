import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

    private static final String BOX_DIMENSIONS = "Advent of code/2015/day2/src/main/resources/presentDimensions.txt";

    public static void main(String[] args) {

        File file = new File(BOX_DIMENSIONS);
        calculetePaperNeeds(file);
        calculateRibbonNeeds(file);
    }

    private static List<String> readingBoxesList(File file) {
        List<String> boxesList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            boxesList = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return boxesList;
    }

    private static void calculetePaperNeeds(File file) {
        int paperArea = 0;
        List<String> boxes = readingBoxesList(file);
        String[] dimensions;
        Comparator<String> comparator = Comparator.comparingInt(Integer::parseInt);

        for (String box : boxes) {
            dimensions = box.split("x");
            paperArea += calculatePaperForOneBox(Arrays.stream(dimensions).sorted(comparator).collect(Collectors.toList()));
        }
        System.out.println("Paper needed to pack the presents: " + paperArea + " sqare feet");
    }

    private static int calculatePaperForOneBox(List<String> dimensions) {
        int l = Integer.parseInt(dimensions.get(0));
        int w = Integer.parseInt(dimensions.get(1));
        int h = Integer.parseInt(dimensions.get(2));
        int smallestSide = l * w;

        return 2 * l * w + 2 * l * h + 2 * w * h + smallestSide;
    }

    private static void calculateRibbonNeeds(File file) {
        int ribbonLength = 0;
        List<String> boxes = readingBoxesList(file);
        String[] dimensions;
        Comparator<String> comparator = Comparator.comparingInt(Integer::parseInt);

        for (String box : boxes) {
            dimensions = box.split("x");
            ribbonLength += calculateRibbonForOneBox(Arrays.stream(dimensions).sorted(comparator).collect(Collectors.toList()));
        }
        System.out.println("Ribbon needed to wrap the presents: " + ribbonLength + " feet");
    }

    private static int calculateRibbonForOneBox(List<String> dimensions) {
        int l = Integer.parseInt(dimensions.get(0));
        int w = Integer.parseInt(dimensions.get(1));
        int h = Integer.parseInt(dimensions.get(2));
        int bow = l * w * h;

        return 2 * l + 2 * w + bow;
    }
}
