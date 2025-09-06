package src;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {

        String nomeArquivo = "src/entrada.txt"; //armazena o nome do arquivo txt

        // leitura dos numeros e criacao da lista
        List<Integer> numeros = new ArrayList<>(); // cria uma lista para armazenar os números lidos do arquivo txt
        LerNumeros.lerNumeros(nomeArquivo, numeros); // chama a função da classe LerNumeros para armazenar os números lidos do arquivo txt na lista
        System.out.println("Números lidos do arquivo: " + numeros); // exibe os números lidos do arquivo

        ArrayList<Integer> ordemMoto1 = new ArrayList<>(); // cria uma lista para armazenar a ordem de entregas do moto 1
        ArrayList<Integer> ordemMoto2 = new ArrayList<>(); // cria uma lista para armazenar a ordem de entregas do moto 2

        long tempoInicial = System.nanoTime(); // armazena o tempo inicial em nanosegundos

        Resultado resultados = Calculos.explorarPossibilidades(numeros, 0, ordemMoto1, ordemMoto2); // chama a função para explorar todas as possibilidades de distribuição das entregas entre os dois motoqueiros

        long tempoFinal = System.nanoTime(); // armazena o tempo final em nanosegundos

        long duracao = tempoFinal - tempoInicial; // calcula a duração da execução em nanosegundos

        // para poucas entradas
        long duracaoEmMilissegundos = TimeUnit.NANOSECONDS.toMillis(duracao); // converte a duração para milissegundos
        System.out.println("Duracao da execucao: " + duracaoEmMilissegundos + " ms"); // exibe a duração da execução em milissegundos

        long duracaoEmSegundos = TimeUnit.NANOSECONDS.toSeconds(duracao); // converte a duração para segundos
        System.out.println("Duracao da execucao: " + duracaoEmSegundos + " s"); // exibe a duração da execução em segundos

        resultados.getOrdemMoto1().sort(Integer::compareTo); // ordena a lista de entregas do moto 1
        resultados.getOrdemMoto2().sort(Integer::compareTo); // ordena a lista de entregas do moto 2

        try (PrintWriter writer = new PrintWriter(new FileWriter("Projeto_Entregas/src/saida.txt"))) { // cria um arquivo txt para armazenar os resultados
            writer.println("=> Melhor tempo: " + resultados.getTempoMinimo()); // escreve o tempo mínimo no arquivo txt
            writer.println("*Moto 1: "); 
            for (int i = 0; i < resultados.getOrdemMoto1().size(); i++) { // escreve a ordem de entregas do moto 1 no arquivo txt
                writer.println(resultados.getOrdemMoto1().get(i));
            }
            writer.println("*Moto 2: "); 
            for (int i = 0; i < resultados.getOrdemMoto2().size(); i++) { // escreve a ordem de entregas do moto 2 no arquivo txt
                writer.println(resultados.getOrdemMoto2().get(i));
            }
            System.out.println("Resultados escritos em 'saida.txt' com sucesso."); // exibe uma mensagem de sucesso
        } catch (Exception e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage()); // exibe uma mensagem de erro caso ocorra algum problema ao escrever no arquivo txt

        }

    }

}