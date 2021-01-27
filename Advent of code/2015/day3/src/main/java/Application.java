import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Application {

    public static void main(String[] args) {

        File fileIn = new File("Advent of code/2015/day3/src/main/resources/instructionsForSanta.txt");
        String instructions = readFile(fileIn);

        Set<House> housesPartOne = deliverPresentsBySanta(Objects.requireNonNull(instructions));
        Set<House> housesPartTwo = deliverPresentsBySantaAndRobosanta(instructions);

        System.out.println("Ilość odwiedzonych domów przez Mikołaja: " + housesPartOne.size());
        System.out.println("Ilość odwiedzonych domów przez Mikołaja oraz robota: " + housesPartTwo.size());
    }

    private static String readFile(File fileIn) {
        try {
            Scanner reader = new Scanner(fileIn);
            return reader.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Set<House> deliverPresentsBySanta(String instructions) {
        int x = 0;
        int y = 0;
        Set<House> houses = new HashSet<>();
        House house = new House(x, y);
        houses.add(house);
        for (char val : instructions.toCharArray()) {
            switch (val) {
                case '^' -> x++;
                case 'v' -> x--;
                case '>' -> y++;
                case '<' -> y--;
            }
            House newHouse = new House(x, y);
            if (houses.stream().noneMatch((v) -> newHouse.getPositionX() == v.getPositionX() && newHouse.getPositionY() == v.getPositionY())) {
                houses.add(new House(x, y));
            }
        }
        return houses;
    }

    private static Set<House> deliverPresentsBySantaAndRobosanta(String instructions) {
        String instructionsForSanta = "";
        String instructionsForRobot = "";
        for (int i = 0; i < instructions.length(); i++) {
            if (i % 2 == 0) {
                instructionsForSanta = instructionsForSanta.concat(String.valueOf(instructions.charAt(i)));
            } else {
                instructionsForRobot = instructionsForRobot.concat(String.valueOf(instructions.charAt(i)));
            }
        }
        Set<House> santaHouses = deliverPresentsBySanta(instructionsForSanta);
        Set<House> robotHouses = deliverPresentsBySanta(instructionsForRobot);

        santaHouses.forEach(house -> {
            if (robotHouses.stream().noneMatch(h -> house.getPositionX() == h.getPositionX() && house.getPositionY() == h.getPositionY())) {
                robotHouses.add(house);
            }
        });
        return robotHouses;
    }
}
