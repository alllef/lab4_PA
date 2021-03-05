import java.util.ArrayList;

public class State {

    State parent;
    Rib removedRib;
    int alpha;
    int beta;
    int value;
    int depth;

    HackenBashGraph graph;
    ArrayList<State> stateList = new ArrayList<>();

    State(State parentState, HackenBashGraph graph, int depth,Rib removedRib) {
        this.parent = parentState;
        this.graph = graph;
        this.depth = depth;
    }


    int calculateMainFunction() {

        if (graph.redColor == 0) value = Integer.MIN_VALUE;
        else if (graph.blueColor == 0) value = Integer.MAX_VALUE;
        else value = graph.redColor - graph.blueColor;
        return value;
    }

    boolean calculateMinMax() {
        if(parent==null)return false;
        if (parent.depth % 2 == 0) {
            parent.value = Math.max(value, parent.alpha);
            parent.alpha = parent.value;
        } else {
            parent.value = Math.min(value, parent.beta);
            parent.beta = value;
        }
        return parent.beta <= parent.alpha;
    }


}
