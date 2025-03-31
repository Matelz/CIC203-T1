import javax.swing.*;
import java.awt.*;

public class TesteDeDesempenho {
  private static void initGUI(String uiTitle) {
    final JComboBox<String> c1;
    JFrame frame = new JFrame(uiTitle);
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

    JButton startBtn = new JButton("Começar");

    JLabel title = new JLabel("Teste de Desempenho - CIC203-T1");
    JLabel signature = new JLabel("Por: Mateus Silva Pereira - (24.00511-8)");

    JProgressBar pBar = new JProgressBar();
    pBar.setMaximum(100);
    pBar.setMinimum(0);
    pBar.setEnabled(true);

    String[] algoritimos = { "Teste", "Teste 2", "Teste 3" };
    c1 = new JComboBox<String>(algoritimos);

    JTextField input1 = new JTextField("", 10);

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
    gBagConstraints.gridwidth = 2;
    panel.add(startBtn, gBagConstraints);

    gBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gBagConstraints.gridx = 0;
    gBagConstraints.gridy = 4;
    gBagConstraints.gridwidth = 2;
    panel.add(signature, gBagConstraints);

    frame.add(panel);

    frame.setVisible(true);
  }

  public static void main(String[] args) {
    initGUI("Teste de Desempenho");
  }
}
