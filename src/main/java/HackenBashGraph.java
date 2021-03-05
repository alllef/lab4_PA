import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HackenBashGraph {

    static HackenBashGraph currGraph = new HackenBashGraph();
    static ArrayList<Integer> groundDots = new ArrayList<>();

    static {
        generateFromFile();
        groundDots.add(1);
    }

    ArrayList<Rib> ribsList = new ArrayList<>();
    int redColor = 0;
    int blueColor = 0;

    static void generateFromFile() {
        File file = new File("src\\main\\resources\\Graph.txt");
        try {
            FileReader reader = new FileReader(file);
            Scanner scan = new Scanner(reader);

            while (scan.hasNextLine()) {
                String tmp = scan.nextLine();
                String[] stringNumbers = tmp.split(" ");
                Rib rib = new Rib(Integer.parseInt(stringNumbers[0]), Integer.parseInt(stringNumbers[1]), Rib.Color.values()[Integer.parseInt(stringNumbers[2])]);
                currGraph.ribsList.add(rib);
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    void removeFallenRibs() {
        Queue<Integer> queue = new LinkedList<>();
        HashMap<Rib, Boolean> isUsed = new HashMap<>();
        for (Integer dot : groundDots) {
            queue.add(dot);

            while (!queue.isEmpty()) {


                for (Rib rib : ribsList) {
                    if (isUsed.get(rib) == null) {
                        if (rib.firstDot == queue.peek()) queue.add(rib.secondDot);
                        if (rib.secondDot == queue.peek()) queue.add(rib.firstDot);
                        isUsed.put(rib, true);
                    }

                }
                queue.remove();
            }

        }

        ribsList.removeIf(rib -> isUsed.get(rib) == null);

    }

    void recalculateColors() {


        for (Rib rib : ribsList) {
            if (rib.color == Rib.Color.red) redColor++;
            else blueColor++;
        }

    }

    boolean isGameOver() {
        return redColor == 0 || blueColor == 0;
    }

    public static void main( String[] args){
        HackenBashGraph testGraph = HackenBashGraph.currGraph;
        testGraph.ribsList.remove(0);
        testGraph.removeFallenRibs();
    }
}
