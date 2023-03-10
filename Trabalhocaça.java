//Aluno Raicon Zimmermann Look
//SISTEMAS DE INFORMAÇÃO
//Carrard
package trabalhocaça;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Trabalhocaça {

    //Função teste linha - adicionar palavra linha
    public static void insere_linha(char matriz[][], String plv, int i, int j, Random rd) {

        int tam = plv.length(); //variavel que passa o tamanho da palavra
        int linha;
        int coluna;
        boolean aux = false; //variavel auxiliar
        int cont = 0; //contador

        coluna = (rd.nextInt(0, j - 1)); //randomizando um valor válido para coluna
        linha = aleatorio(plv, i, rd); //chama funcao aleatoria

        while (aux == false) {

            for (int l = 0; l < tam; l++) { //garantir não esteja na mesma posição da outra
                if (matriz[linha + 1][coluna] != '*') {
                    cont++;
                }
            }
            if (cont == 0) { //Não passou por nenhuma letra
                aux = true;
            } else { //Caso ele encontrar uma letra na posição ele tentar achar outra posição
                coluna = (rd.nextInt(0, j - 1));
                linha = aleatorio(plv, i, rd);
                cont = 0;
            }
        }
        for (int l = 0; l < tam; l++) { //Insere a palavra na matriz
            matriz[linha + l][coluna] = plv.charAt(l);
        }
    }

    //Função teste coluna - adicionar palavra coluna
    public static void insere_coluna(char matriz[][], String plv, int i, int j, Random rd) { //Semelhante a função insere_linha somente a mudança pela coluna

        int tam = plv.length();
        int linha;
        int coluna;
        boolean aux = false;
        int cont = 0;

        linha = (rd.nextInt(0, i - 1));
        coluna = aleatorio(plv, j, rd);

        while (aux == false) {
            for (int l = 0; l < tam; l++) {
                if (matriz[linha][coluna + l] != '*') {
                    cont++;
                }
            }
            if (cont == 0) {
                aux = true;
            } else {
                linha = (rd.nextInt(0, i - 1));
                coluna = aleatorio(plv, j, rd);
                cont = 0;
            }
        }
        for (int l = 0; l < tam; l++) {

            matriz[linha][coluna + l] = plv.charAt(l);
        }
    }

    //Função gerar posição aleatoria dentro da matriz - retorna a posição dentro da matriz
    public static int aleatorio(String plv, int j, Random rd) {

        int tam = plv.length();
        int valor;

        do {
            valor = (rd.nextInt(0, j - 1)); //
        } while (j - valor < tam);
        return valor;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        int i, j;
        ArrayList<String> lista = new ArrayList(); //lista das palavras armazenadas

        //Pedir tamanho de linhas e colunas do usuário
        System.out.println("Quantas linhas sua matriz tem?");
        i = sc.nextInt();
        System.out.println("Quantas Colunas sua matriz tem?");
        j = sc.nextInt();

        //Começo para pedir as palavras junto as variaveis
        int qtd;
        String palavra;

        //Usuario Digita Quantidade de palavras
        System.out.println("Quantidade de Palavras?");
        qtd = sc.nextInt();

        //Loop para usuário escrever as palavras
        for (int z = 0; z < qtd; z++) { //loop para definir as palavras

            System.out.println("Digite uma palavra!");

            palavra = sc.next().toUpperCase(); //to upper case = transformar palavras minusculas em maiusculas
            lista.add(palavra); // adicionar palavras na lista
        }

        char[][] matriz = new char[i][j]; //Criação da matriz do caça palavras

        for (int l = 0; l < i; l++) {
            for (int c = 0; c < j; c++) {
                matriz[l][c] = '*'; //todos os caracteres transformador em asteriscos
            }
        }

        for (int l = 0; l < lista.size(); l++) { //loop para randomizar a escolha das palavras entre linhas ou colunas
            Random rand = new Random();
            int randomValue = rand.nextInt() % 2; //rand que define entre 0 ou 1

            if (randomValue == 0) {
                insere_linha(matriz, lista.get(l), i, j, rd); //chamar função insere_linha
            } else {
                insere_coluna(matriz, lista.get(l), i, j, rd); //chamar função insere_coluna
            }
        }
        for (int l = 0; l < i; l++) { //loop para TROCAR OS "*" pelas letras aleatórias
            for (int c = 0; c < j; c++) {
                if (matriz[l][c] == '*') { //  
                    matriz[l][c] = (char) (rd.nextInt(65, 90)); //geração de letras aleatórias através da tabela ASCII
                }
            }
        }
        for (int l = 0; l < i; l++) { //loop para printar a matriz
            for (int c = 0; c < j; c++) {
                System.out.printf("%c ", matriz[l][c]); // printar a matriz
            }
            System.out.println("\n");
        }
    }
}
