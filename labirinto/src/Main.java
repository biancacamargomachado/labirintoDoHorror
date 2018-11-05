import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        long time1 = System.currentTimeMillis();
        Resolve lab = new Resolve();
        String c = "4";
        lab.load(c);
        try{
            lab.objetivo();
            lab.setaAdjacentes();

            System.out.println("Distância da entrada p/ saída: "+lab.calcula() + " nodos");
        }catch (Exception e) {
            System.out.println("Problema em Path! " + e);
        }
        long time2 = System.currentTimeMillis();
        long tempo = time2 - time1;
        System.out.format("Tempo: %,8d (ms)", tempo);
    }
}
