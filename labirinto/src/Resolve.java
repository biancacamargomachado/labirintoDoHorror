import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Resolve {
    static Nodo m[][];//matriz de todos os nodos
    static Nodo saida;//nodo de saída do labirinto
    static int tamMat;//tamanho da matriz
    static ArrayList ponto = new ArrayList<Integer>();//pontos de entrada e saída
    static int limite = 0;//limites da matriz (tamMat - 1)

    static ArrayList<String> list = new ArrayList();//array list que salva os binários

    public void load(String c) throws IOException {
        Path arq = Paths.get("caso" + c + "c");
        try (Scanner sc = new Scanner(Files.newBufferedReader(arq, Charset.forName("utf8")))) {
            String aux = "";
            String tam[] = sc.nextLine().split(" ");
            tamMat = Integer.parseInt(tam[0]);
            limite = tamMat - 1;
            System.out.println("\n Tamanho da matrix " + tamMat + "x" + tamMat);
            m = new Nodo[tamMat][tamMat];
            while (sc.hasNext()) {
                String valorBin = Integer.toString(Integer.parseInt(sc.next(), 16), 2);
                while (valorBin.length() < 4) {//só para completar os zeros da frente
                    valorBin = '0' + valorBin;
                }
                list.add(valorBin);
            }
            setMat(list);
        } catch (IOException x) {
            System.err.format("Erro de E/S: %s%n", x);
        }
    }

    public void setMat(ArrayList<String> binario) {
        int cont = 0;
        for (int x = 0; x < tamMat; x++) {
            for (int y = 0; y < tamMat; y++) {
                m[x][y] = new Nodo();

                m[x][y].sup = binario.get(cont).charAt(0) - '0';
                m[x][y].dir = binario.get(cont).charAt(1) - '0';
                m[x][y].inf = binario.get(cont).charAt(2) - '0';
                m[x][y].esq = binario.get(cont).charAt(3) - '0';
                m[x][y].linha = x;
                m[x][y].coluna = y;

                System.out.print(m[x][y].sup);
                System.out.print(m[x][y].dir);
                System.out.print(m[x][y].inf);
                System.out.print(m[x][y].esq);
                System.out.print(" ");
                cont++;
            }
            System.out.println("\n");
        }
    }

    public static void objetivo() {//encontra entrada/saída
        for (int i = 0; i < tamMat; i++) {
            if (m[0][i].sup == 0) {//superior
                ponto.add(0);
                ponto.add(i);
            }

            if (m[i][0].esq == 0) {//esquerdo
                ponto.add(i);
                ponto.add(0);
            }

            if (m[limite][i].inf == 0) {//inferior
                ponto.add(limite);
                ponto.add(i);
            }

            if (m[i][limite].dir == 0) {//direito
                ponto.add(i);
                ponto.add(limite);
            }
        }
    }

    public static int calcula(){

        Nodo entrada = m[(int) ponto.get(0)][(int) ponto.get(1)];
        System.out.println("Entrada: \n linha: " + entrada.linha + "\n coluna:" + entrada.coluna);

        saida=m[(int) ponto.get(2)][(int) ponto.get(3)];
        System.out.println("\nSaída: \n linha: " + saida.linha + "\n coluna:" + saida.coluna);

        int result = buscaProf(entrada,0);
        return result;//disntancia
    }

    public static int buscaProf(Nodo nodo, int cont){
        if(nodo==saida)
            return cont;

        nodo.marca=1;//marca como visitado

        for (Nodo n:
                nodo.adjacentes) {
                if (n.marca == 0) {//se nao foi visitado
                    cont++;
                    cont = buscaProf(n, cont);
                }
        }
        return cont;
        }

        public static void setaAdjacentes(){
            for (int i=0;i<tamMat;i++){
                for(int j=0;j<tamMat;j++){

                    if (m[i][j].sup == 0) {//superior
                        Nodo nodo = m[i][j];
                        if (i > 0) {
                            nodo.adjacentes.add(m[i - 1][j]);
                        }
                    }

                    if(m[i][j].dir==0 && (j<limite))//direita
                        m[i][j].adjacentes.add(m[i][j+1]);

                    if(m[i][j].inf==0 && (i<limite))//baixo
                        m[i][j].adjacentes.add(m[i+1][j]);

                    if(m[i][j].esq==0 && (j>0))//esquerda
                        m[i][j].adjacentes.add(m[i][j-1]);
                }
            }
        }
}
