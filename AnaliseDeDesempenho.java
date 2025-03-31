import javax.swing.JFrame;

public class AnaliseDeDesempenho {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Analise de Desempenho");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela

        frame.setVisible(true);

        // Dropdown menu para escolher o algoritmo
        String[] algoritmos = { "Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort" };
        String algoritmoEscolhido = (String) javax.swing.JOptionPane.showInputDialog(frame, "Escolha o algoritmo:", "Algoritmo de Ordenação", javax.swing.JOptionPane.QUESTION_MESSAGE, null, algoritmos, algoritmos[0]);

        // Barra de progresso
        javax.swing.JProgressBar progressBar = new javax.swing.JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setValue(0);
        frame.add(progressBar, java.awt.BorderLayout.SOUTH);

        frame.setVisible(true);
        // Simulação de execução do algoritmo
    }
}
