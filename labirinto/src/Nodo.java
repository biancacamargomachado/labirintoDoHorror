import java.util.ArrayList;
import java.util.List;

public class Nodo {
    int sup;//0 = SEM parede, 1 = PAREDE
    int dir;//0 = SEM parede, 1 = PAREDE
    int inf;//0 = SEM parede, 1 = PAREDE
    int esq;//0 = SEM parede, 1 = PAREDE
    int marca = 0;//0 =  não visitado / 1 = visitado

    int linha;//coordenada do nodo
    int coluna;//coordenada do nodo

    List<Nodo> adjacentes = new ArrayList<>();//nodos adjacentes
}
