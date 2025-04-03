import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class NossoVetor {
    private int ocupacao;
    private int[] vetor;
    private long counter;
    private long startTime;
    private long endTime;
    private long elapsedTime;

    public NossoVetor(int tamanho) {
        vetor = new int[tamanho];
        ocupacao = 0; // por clareza
    }

    public NossoVetor() {
        this(10);
    }

    public void insere(int i) {
        if (estaCheio())
            redimensiona(vetor.length * 2);
        vetor[ocupacao++] = i;
    }

    boolean estaCheio() {
        return ocupacao == vetor.length;
    }

    @Override
    public String toString() {
        String s = "\nocupacao = " + ocupacao + "\n";
        for (int i = 0; i < ocupacao; i++) {
            s += vetor[i] + " ";
        }
        return s + "\n";
    }

    public boolean estaVazio() {
        return ocupacao == 0;
    }

    public int remove() {
        if (estaVazio()) {
            throw new VetorVazioException("vetor vazio, nao a o que remover");
        }
        int aux = vetor[--ocupacao];
        if (vetor.length >= 6 && ocupacao <= vetor.length / 4) {
            redimensiona(vetor.length / 2);
        }
        return aux;
    }

    private void redimensiona(int novoTamanho) {
        int[] temp = new int[novoTamanho];
        for (int i = 0; i < ocupacao; i++)
            temp[i] = vetor[i];
        vetor = temp;
    }

    public int getTamanho() {
        return vetor.length;
    }

    public void preencheVetor() {
        Random random = new Random();
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = random.nextInt(vetor.length * 10);
        }
        ocupacao = vetor.length;
    }

    public boolean contem(int elemento) {
        for (int i = 0; i < ocupacao; i++) {
            if (vetor[i] == elemento) {
                return true;
            }
        }
        return false;
    }

    public int indiceDe(int elemento) {
        if (estaVazio())
            throw new VetorVazioException("vetor vazio");
        for (int i = 0; i < ocupacao; i++) {
            if (vetor[i] == elemento) {
                return i;
            }
        }
        throw new ElementoNaoEncontradoException(elemento + " nao encontrado");
    }

    public void bubbleSort() {
        if (estaVazio()) {
            throw new VetorVazioException("vetor vazio, nao a o que ordenar");
        }
        startTime = 0;
        endTime = 0;
        elapsedTime = 0;

        startTime = System.currentTimeMillis();

        for (int vez = 1; vez < vetor.length; vez++) {
            for (int i = 0; i < vetor.length - vez; i++) {
                if (vetor[i] > vetor[i + 1]) {
                    int aux = vetor[i];
                    vetor[i] = vetor[i + 1];
                    vetor[i + 1] = aux;
                }
                counter++;
            }
        }

        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;

        System.out.println(elapsedTime + "ms, " + counter);
    }

    public void selectionSort(boolean shouldLog) {
        if (estaVazio()) {
            throw new VetorVazioException("vetor vazio, nao a o que ordenar");
        }
        startTime = 0;
        endTime = 0;
        elapsedTime = 0;

        startTime = System.currentTimeMillis();

        for (int i = 0; i < vetor.length - 1; ++i) {
            int min = i;
            for (int j = i + 1; j < vetor.length; ++j)
                if (vetor[j] < vetor[min])
                    min = j;
            counter++;
            int x = vetor[i];
            vetor[i] = vetor[min];
            vetor[min] = x;
        }

        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;

        if (shouldLog) {
            System.out.println(elapsedTime + "ms, " + counter);
        }
    }

    public void insertionSort() {
        if (estaVazio()) {
            throw new VetorVazioException("vetor vazio, nao a o que ordenar");
        }
        startTime = 0;
        endTime = 0;
        elapsedTime = 0;

        startTime = System.currentTimeMillis();

        for (int j = 1; j < vetor.length; ++j) {
            int x = vetor[j];
            int i;
            for (i = j - 1; i >= 0 && vetor[i] > x; --i)
                vetor[i + 1] = vetor[i];
            counter++;
            vetor[i + 1] = x;
        }

        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;

        System.out.println(elapsedTime + "ms, " + counter);
    }

    public void linearSearch(int target) {
        target = vetor[target];

        int counter = 0;
        int foundElement = -1;
        startTime = System.nanoTime();
        
        for (int i = 0; i < this.vetor.length; i++) {
            counter++;
            if (this.vetor[i] == target) {
                foundElement = this.vetor[i];
                break;
            }
        }
        
        endTime = System.nanoTime();
        double elapsedTime = (endTime - startTime) / 1_000_000_000.0;
        
        System.out.printf("%.5f, %d, %d, %d%n", elapsedTime, counter, foundElement, this.vetor.length);
    }

    public void binarySearch(int target) {
        target = vetor[target];

        int left = 0;
        int right = this.vetor.length - 1;
        int counter = 0;
        int foundElement = -1; // Default if not found
        startTime = System.nanoTime();

        while (left <= right) {
            counter++;
            int mid = left + (right - left) / 2;

            if (this.vetor[mid] == target) {
                foundElement = this.vetor[mid];
                break;
            } else if (this.vetor[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        endTime = System.nanoTime();
        double elapsedTime = (endTime - startTime) / 1_000_000_000.0;

        // Convert to BigDecimal for better precision
        // and round to 10 decimal places
        BigDecimal bd = new BigDecimal(elapsedTime).setScale(10, RoundingMode.HALF_UP);

        System.out.printf("%s, %d, %d, %d%n", bd.toPlainString(), counter, foundElement, this.vetor.length);
    }

    public long getCounter() {
        return counter;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }
}

class VetorVazioException extends RuntimeException {
    public VetorVazioException(String msg) {
        super(msg);
    }
}

class ElementoNaoEncontradoException extends RuntimeException {
    public ElementoNaoEncontradoException(String msg) {
        super(msg);
    }
}
