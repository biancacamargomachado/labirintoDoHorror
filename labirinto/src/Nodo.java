import java.util.ArrayList;
import java.util.List;

public class Nodo {
    int sup;//contém 0 ou um, sendo 0 = NÃO tem parede e 1 tem parede
    int dir;
    int inf;
    int esq;
    int marca = 0;//para marcar se o nodo ja foi percorrido: incia com 0 =  branco = não percorrido =  não sei se vou precisar da marca aqui
                                                            //1 = preto = visitados inclusive seus decendentes
                                                            //2 percorrido duas vezes
    int linha;
    int coluna;
    List<Nodo> adjacentes = new ArrayList<>();
}
