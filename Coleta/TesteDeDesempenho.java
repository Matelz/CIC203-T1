public class TesteDeDesempenho {
  public static void main(String[] args) {
    String[] algoritimos = { "Bubble Sort", "Selection Sort", "Insertion Sort", "Linear Search", "Binary Search" };

    GUI gui = new GUI("Teste de Desempenho", algoritimos);
    gui.initGUI();
  }
}
