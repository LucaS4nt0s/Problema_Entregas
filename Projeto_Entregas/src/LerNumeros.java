package src;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class LerNumeros {
    public static void lerNumeros(String nomeArquivo, List<Integer> numeros) {

        InputStream inputStream = LerNumeros.class.getResourceAsStream("/" + nomeArquivo); // transforma o nome do arquivo em um caminho

        if (inputStream != null) { // verifica se o caminho é valido
            try (Scanner scanner = new Scanner(inputStream)) { // cria um scanner para ler o arquivo
                while (scanner.hasNextInt()) { // enquanto houver números inteiros no arquivo
                    numeros.add(scanner.nextInt()); // adiciona o número lido na lista
                }
            }
        } else {
            System.err.println("Arquivo não encontrado ou inacessível: " + nomeArquivo); // exibe uma mensagem de erro caso o arquivo não seja encontrado
        }
    }
}
