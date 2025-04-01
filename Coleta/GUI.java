import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI {
  public interface buttonAction {
    void action();
  }

  private String title;
  private String[] algoritimos;
  private JFrame frame;
  private String option;
  public int vecSize;

  private buttonAction action;

  public GUI(String title, String[] algoritimos, buttonAction action) {
    this.title = title;
    this.algoritimos = algoritimos;
    this.action = action;
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

    JButton startBtn = new JButton("Começar");

    JLabel title = new JLabel("Teste de Desempenho - CIC203-T1");
    JLabel signature = new JLabel("Por: Mateus Silva Pereira - (24.00511-8)");

    c1 = new JComboBox<String>(algoritimos);
    c1.addActionListener(e -> {
      option = (String) c1.getSelectedItem();
    });
    c1.setSelectedIndex(0);

    JTextField input1 = new JTextField("", 10);

    startBtn.addActionListener(e -> {
      String input = input1.getText();
      try {
        this.vecSize = Integer.parseInt(input);
      } catch (NumberFormatException ex) {
        System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
        return;
      }

      this.action.action();
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
}
