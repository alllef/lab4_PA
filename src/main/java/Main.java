import java.io.File;

public class Main {
    public static void main( String[] args){
        StateTree tmp = new StateTree();
        tmp.makeStatesTree(2);
        tmp.minMaxAlphaBetaAlgorithm();
    }
}
