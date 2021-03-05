import java.util.*;

public class StateTree {

    State state;


    void makeStatesTree(int stopDepth) {

        state = new State(null, HackenBashGraph.currGraph, 0,null);
        Queue<State> stateQueue = new LinkedList<>();
        stateQueue.add(state);

        while (!stateQueue.isEmpty()) {
            State curr = stateQueue.peek();

            for (Rib rib : curr.graph.ribsList) {
                if (rib.color == Rib.Color.values()[curr.depth % 2]) {

                    HackenBashGraph tmpGraph = new HackenBashGraph();

                    for (Rib tmp : curr.graph.ribsList) {
                        if (tmp != rib) tmpGraph.ribsList.add(new Rib(tmp.firstDot, tmp.secondDot, tmp.color));
                    }

                    tmpGraph.removeFallenRibs();
                    tmpGraph.recalculateColors();

                    State tmp = new State(curr, tmpGraph, curr.depth + 1,new Rib(rib.firstDot, rib.secondDot,rib.color));
                    curr.stateList.add(tmp);

                    if (tmp.depth == stopDepth || tmpGraph.isGameOver()) tmp.calculateMainFunction();
                    else stateQueue.add(tmp);
                }
            }

            stateQueue.poll();
        }
    }

    void minMaxAlphaBetaAlgorithm() {

        state.alpha = Integer.MIN_VALUE;
        state.beta = Integer.MAX_VALUE;


        Stack<State> stateStack = new Stack<>();
        HashMap<State, Boolean> isUsed = new HashMap<>();

        stateStack.push(state);

        while (!stateStack.empty()) {


            while (hasUnusedChildren(stateStack.peek(), isUsed)) {
                for (State st : stateStack.peek().stateList) {
                    if (isUsed.get(st) == null) {

                        stateStack.push(st);
                        isUsed.put(st, true);

                        stateStack.peek().alpha = stateStack.peek().parent.alpha;
                        stateStack.peek().beta = stateStack.peek().parent.beta;

                        break;
                    }
                }
            }

            boolean hasToReturn;

            do {

                hasToReturn = stateStack.peek().calculateMinMax();
                stateStack.pop();
            } while (hasToReturn);

        }
    }

    boolean hasUnusedChildren(State state, HashMap<State, Boolean> isUsed) {

        for (State tmp : state.stateList) {
            if (isUsed.get(tmp) == null) return true;
        }

        return false;
    }
}
