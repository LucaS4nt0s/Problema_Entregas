package src;
import java.util.ArrayList;
import java.util.List;

public class Calculos {
    public static Resultado explorarPossibilidades(List<Integer> todosOsTempos, int indice, List<Integer> listaMoto1, List<Integer> listaMoto2) {
        // Se o índice alcançou o fim da lista, significa que todas as pizzas foram distribuídas.
        // Agora, calculamos o custo final para esta combinação específica.
        // caso base
        if (indice == todosOsTempos.size()) {
            int custoFinal = calcularCustoDaDivisao(listaMoto1, listaMoto2); // calcula o custo da divisão atual
            return new Resultado(custoFinal, listaMoto1, listaMoto2); // retorna o resultado com o custo final e as listas de entregas dos motoqueiros
        }

        int pizzaAtual = todosOsTempos.get(indice); // pega o tempo da pizza atual a ser distribuída

        // Opção 1: Dar a pizza atual para o Motoqueiro 1.
        // Cria uma nova lista para não interferir com a outra chamada recursiva.
        List<Integer> novaListaMoto1 = new ArrayList<>(listaMoto1);
        novaListaMoto1.add(pizzaAtual); // adiciona a pizza atual à nova lista do motoqueiro 1
        Resultado resultadoOpcao1 = explorarPossibilidades(todosOsTempos, indice + 1, novaListaMoto1, listaMoto2); // chama recursivamente a função para o próximo índice passando a nova lista como parametro


        // Opção 2: Dar a pizza atual para o Motoqueiro 2.
        // Cria uma nova lista para a moto2 para não interferir com a outra chamada recursiva.
        List<Integer> novaListaMoto2 = new ArrayList<>(listaMoto2);
        novaListaMoto2.add(pizzaAtual); // adiciona a pizza atual à nova lista do motoqueiro 2
        Resultado resultadoOpcao2 = explorarPossibilidades(todosOsTempos, indice + 1, listaMoto1, novaListaMoto2); // chama recursivamente a função para o próximo índice passando a nova lista como parametro

        // A melhor solução é o MÍNIMO entre os resultados das duas opções.
        return resultadoOpcao1.getTempoMinimo() < resultadoOpcao2.getTempoMinimo() ? resultadoOpcao1 : resultadoOpcao2;
    }

    /**
     * Calcula o tempo final gasto para uma divisão específica das entregas.
     * O tempo final é o do motoqueiro que demorar mais (o "gargalo" da operação).
     */
    private static int calcularCustoDaDivisao(List<Integer> listaMoto1, List<Integer> listaMoto2) {
        int custo1 = calcularCustoMotoqueiro(listaMoto1); // calcula o custo do motoqueiro 1
        int custo2 = calcularCustoMotoqueiro(listaMoto2); // calcula o custo do motoqueiro 2
        
        // Descomente as linhas abaixo para ver cada uma das 2^n possibilidades
        // System.out.println("Divisão: " + listaMoto1 + " | " + listaMoto2 + 
        //                    " -> Custos: [" + custo1 + ", " + custo2 + "]" + 
        //                    " -> Tempo Final: " + Math.max(custo1, custo2));

        return Math.max(custo1, custo2); // retorna o maior custo entre os dois motoqueiros
    }

    private static int calcularCustoMotoqueiro(List<Integer> lista) { // função que calcula o custo de um motoqueiro (chamada na funcão calcularCustoDaDivisao)
        // Se o motoqueiro não fez nenhuma entrega, o custo é 0.
        if (lista.isEmpty()) {
            return 0;
        }

        int soma = 0; // inicializa a soma dos tempos
        int maior = 0; // inicializa o maior tempo (apenas tempos maiores que 0)
        for (int tempo : lista) { // para cada tempo na lista
            soma += tempo; // acumula o tempo na soma
            if (tempo > maior) {
                maior = tempo; // atualiza o maior tempo, se necessário
            }
        }

        return (2 * soma) - maior; // multiplica os tempos e subtrai o maior tempo (considera que é a última entrega)
    }
}
