package src;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String nomeArquivo = "src/entrada.txt"; //armazena o nome do arquivo txt

        // leitura dos numeros e criacao da lista
        List<Integer> numeros = new ArrayList<>(); // cria uma lista para armazenar os números lidos do arquivo txt
        LerNumeros.lerNumeros(nomeArquivo, numeros); // chama a função da classe LerNumeros para armazenar os números lidos do arquivo txt na lista
        System.out.println("Números lidos do arquivo: " + numeros); // exibe os números lidos do arquivo

        ArrayList<Integer> ordemMoto1 = new ArrayList<>(); // cria uma lista para armazenar a ordem de entregas do moto 1
        ArrayList<Integer> ordemMoto2 = new ArrayList<>(); // cria uma lista para armazenar a ordem de entregas do moto 2

        Resultado resultados = explorarPossibilidades(numeros, 0, ordemMoto1, ordemMoto2); // chama a função para explorar todas as possibilidades de distribuição das entregas entre os dois motoqueiros

        resultados.getOrdemMoto1().sort(Integer::compareTo); // ordena a lista de entregas do moto 1
        resultados.getOrdemMoto2().sort(Integer::compareTo); // ordena a lista de entregas do moto 2
        
        System.out.println("O tempo mínimo para concluir todas as entregas é: " + resultados.getTempoMinimo());
        System.out.println("Ordem de entregas do Motoqueiro 1: " + resultados.getOrdemMoto1());
        System.out.println("Ordem de entregas do Motoqueiro 2: " + resultados.getOrdemMoto2());

    }

    private static Resultado explorarPossibilidades(List<Integer> todosOsTempos, int indice, List<Integer> listaMoto1, List<Integer> listaMoto2) {
        // Se o índice alcançou o fim da lista, significa que todas as pizzas foram distribuídas.
        // Agora, calculamos o custo final para esta combinação específica.
        if (indice == todosOsTempos.size()) {
            int custoFinal = calcularCustoDaDivisao(listaMoto1, listaMoto2);
            return new Resultado(custoFinal, listaMoto1, listaMoto2);
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