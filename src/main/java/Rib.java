
public class Rib {

    public enum Color {red, blue}

    int firstDot;
    int secondDot;
    Color color;

    Rib(int firstDot, int secondDot) {
        this.firstDot = firstDot;
        this.secondDot = secondDot;
    }

    Rib(int firstDot, int secondDot,Color color) {
        this.firstDot = firstDot;
        this.secondDot = secondDot;
        this.color = color;
    }
}
