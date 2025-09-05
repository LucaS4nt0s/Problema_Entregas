package src;
import java.util.List;

public class Resultado {
    private int tempoMinimo;
    private List<Integer> ordemMoto1;
    private List<Integer> ordemMoto2;

    public Resultado(int tempoMinimo, List<Integer> ordemMoto1, List<Integer> ordemMoto2) {
        this.tempoMinimo = tempoMinimo;
        this.ordemMoto1 = ordemMoto1;
        this.ordemMoto2 = ordemMoto2;
    }

    public int getTempoMinimo() {
        return tempoMinimo;
    }

    public List<Integer> getOrdemMoto1() {
        return ordemMoto1;
    }

    public List<Integer> getOrdemMoto2() {
        return ordemMoto2;
    }

    @Override
    public String toString() {
        return "Resultado{" +
                "tempoMinimo=" + tempoMinimo +
                ", ordemMoto1=" + ordemMoto1 +
                ", ordemMoto2=" + ordemMoto2 +
                '}';
    }
}
