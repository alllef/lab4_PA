public class Human implements Player {
    @Override
    public void makeMove() {
        HackenBashGraph.currGraph.ribsList.remove(0);
        HackenBashGraph.currGraph.removeFallenRibs();
        HackenBashGraph.currGraph.recalculateColors();
    }
}
