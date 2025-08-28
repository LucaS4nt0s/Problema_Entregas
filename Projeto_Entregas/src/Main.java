package src;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        String nomeArquivo = "src/entrada.txt"; //armazena o nome do arquivo txt

        // leitura dos numeros e criacao da lista
        List<Integer> numeros = new ArrayList<>(); // cria uma lista para armazenar os números lidos do arquivo txt
        LerNumeros.lerNumeros(nomeArquivo, numeros); // chama a função da classe LerNumeros para armazenar os números lidos do arquivo txt na lista
        System.out.println("Números lidos do arquivo: " + numeros); // exibe os números lidos do arquivo

        int maior, moto1, moto2;

        List<Integer> ordemMoto1 = new ArrayList<>(); // cria uma lista para armazenar a ordem da moto1
        List<Integer> ordemMoto2 = new ArrayList<>(); // cria uma lista para armazenar a ordem da moto2
        
        Collections.sort(numeros, Collections.reverseOrder()); // ordena a lista em ordem decrescente

        moto1 = numeros.get(0); // atribui o valor do primeiro elemento da lista à moto1
        moto2 = numeros.get(1); // atribui o valor do segundo elemento da lista à moto2

        ordemMoto1.add(moto1); // adiciona o valor da moto1 à lista de ordem da moto1
        ordemMoto2.add(moto2); // adiciona o valor da moto2 à lista de ordem da moto2

        numeros.remove(0); // remove o primeiro elemento da lista
        numeros.remove(0); // remove o segundo elemento da lista

        for(int i = 0; i < numeros.size(); i++) { // percorre a lista
            numeros.set(i, numeros.get(i) * 2); // dobra o valor de cada elemento da lista
        }

        for(int i = numeros.size() - 1; i >= 0; i--) { // percorre a lista
            if(moto1 <= moto2) { // se o número atual for maior que o valor da moto1
                moto1 += numeros.get(i); // atribui o valor da moto2 ao número atual
                ordemMoto1.add(numeros.get(i)/2); // adiciona o valor da moto1 à lista de ordem da moto1
            }else {
                moto2 += numeros.get(i); // atribui o valor da moto1 ao número atual
                ordemMoto2.add(numeros.get(i)/2); // adiciona o valor da moto2 à lista de ordem da moto2
            }
        }

        // inverte a lista para exibir na ordem correta
        Collections.reverse(ordemMoto1);
        Collections.reverse(ordemMoto2);

        System.out.println("Total Moto 1: " + moto1); // exibe o valor total da moto1
        System.out.println("Ordem Moto 1: " + ordemMoto1); // exibe a ordem dos valores da moto1
        System.out.println("==============================");
        System.out.println("Total Moto 2: " + moto2); // exibe o valor total da moto2
        System.out.println("Ordem Moto 2: " + ordemMoto2); // exibe a ordem dos valores da moto2

    }
}