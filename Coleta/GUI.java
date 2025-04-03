import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI {
  private String title;
  private String[] algoritimos;
  private JFrame frame;
  private String option;
  private int repetitions;
  public int vecSize;

  public GUI(String title, String[] algoritimos) {
    this.title = title;
    this.algoritimos = algoritimos;
  }

  public void buttonAction() {
    // Execute the selected algorithm based on the option and vecSize
    for (int i = 0; i < repetitions; i++) {
      NossoVetor vetor = new NossoVetor(this.vecSize);
      vetor.preencheVetor();
      switch (option) {
        case "Bubble Sort":
          vetor.bubbleSort();
          break;
        case "Selection Sort":
          vetor.selectionSort(true);
          break;
        case "Insertion Sort":
          vetor.insertionSort();
          break;
        case "Linear Search":
          Random rand = new Random();
          int randomIndex = rand.nextInt(this.vecSize);

          vetor.linearSearch(randomIndex);
          break;
        case "Binary Search":
          vetor.selectionSort(false);
          Random rand2 = new Random();
          int randomIndex2 = rand2.nextInt(this.vecSize);
          vetor.binarySearch(randomIndex2);
          break;
        default:
          System.out.println("Algoritmo não reconhecido.");
      }
    }

    JOptionPane.showMessageDialog(frame, "Teste de desempenho concluído!\n" +
        "Algoritmo: " + option + "\n" +
        "Tamanho do vetor: " + vecSize + "\n" +
        "Repetições: " + repetitions);
  }

  public JFrame getFrame() {
    return frame;
  }

  public void initGUI() {
    final JComboBox<String> c1;
    frame = new JFrame(this.title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setSize(400, 300);

    JPanel panel = new JPanel();
    GridBagLayout layout = new GridBagLayout();
    panel.setLayout(layout);
    GridBagConstraints gBagConstraints = new GridBagConstraints();
    gBagConstraints.ipady = 5;
    gBagConstraints.insets = new Insets(10, 5, 10, 5);

    JLabel label1 = new JLabel("Selecione o algorítimo:");
    JLabel label2 = new JLabel("Digite a quantidade de elementos:");
    JLabel label3 = new JLabel("Digite a quantidade de repetições:");

    JButton startBtn = new JButton("Começar");

    JLabel title = new JLabel("Teste de Desempenho - CIC203-T1");
    JLabel signature = new JLabel("Por: Mateus Silva Pereira - (24.00511-8)");

    c1 = new JComboBox<String>(algoritimos);
    c1.addActionListener(e -> {
      option = (String) c1.getSelectedItem();
    });
    c1.setSelectedIndex(0);

    JTextField input1 = new JTextField("", 10);
    JTextField input2 = new JTextField("1", 10);

    startBtn.addActionListener(e -> {
      String input = input1.getText();
      String repetitions = input2.getText();

      if (input.isEmpty() || repetitions.isEmpty()) {
        System.out.println("Por favor, preencha todos os campos.");
        return;
      }

      try {
        this.vecSize = Integer.parseInt(input);
        this.repetitions = Integer.parseInt(repetitions);
      } catch (NumberFormatException ex) {
        System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
        return;
      }

      this.buttonAction();
    });

    gBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gBagConstraints.anchor = GridBagConstraints.CENTER;
    gBagConstraints.gridx = 0;
    gBagConstraints.gridy = 0;
    gBagConstraints.gridwidth = 2;
    panel.add(title, gBagConstraints);

    gBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gBagConstraints.gridx = 0;
    gBagConstraints.gridy = 1;
    gBagConstraints.gridwidth = 1;
    panel.add(label1, gBagConstraints);
    gBagConstraints.gridx = 1;
    panel.add(c1, gBagConstraints);

    gBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gBagConstraints.gridx = 0;
    gBagConstraints.gridy = 2;
    gBagConstraints.gridwidth = 1;
    panel.add(label2, gBagConstraints);
    gBagConstraints.gridx = 1;
    panel.add(input1, gBagConstraints);

    gBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gBagConstraints.gridx = 0;
    gBagConstraints.gridy = 3;
    gBagConstraints.gridwidth = 1;
    panel.add(label3, gBagConstraints);
    gBagConstraints.gridx = 1;
    panel.add(input2, gBagConstraints);

    gBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gBagConstraints.gridx = 0;
    gBagConstraints.gridy = 4;
    gBagConstraints.gridwidth = 2;
    panel.add(startBtn, gBagConstraints);

    gBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gBagConstraints.gridx = 0;
    gBagConstraints.gridy = 5;
    gBagConstraints.gridwidth = 2;
    panel.add(signature, gBagConstraints);

    frame.add(panel);

    frame.setVisible(true);
  }
}
