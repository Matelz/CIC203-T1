import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ProgressMonitor;

public class GUI {
  private String title;
  private String[] algoritimos;
  private JFrame frame;

  public GUI(String title, String[] algoritimos) {
    this.title = title;
    this.algoritimos = algoritimos;
  }

  public JFrame getFrame() {
    return frame;
  }

  private void buttonAction() {
    ProgressMonitor progressMonitor = new ProgressMonitor(frame, "Processando...", "", 0, 100);
    progressMonitor.setProgress(0);
    progressMonitor.setMillisToPopup(0);
    progressMonitor.setProgress(0);
    progressMonitor.setMaximum(100);
    progressMonitor.setMinimum(0);

    new Thread() {
      public void run() {
        // Simula o algoritmo de ordenação
        for (int i = 0; i <= 100; i++) {
          try {
            Thread.sleep(50);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          progressMonitor.setProgress(i);
          progressMonitor.setNote("Processando " + i + "%");
        }
      }
    }.start();
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
    startBtn.addActionListener(e -> {
        this.buttonAction();
    });

    JLabel title = new JLabel("Teste de Desempenho - CIC203-T1");
    JLabel signature = new JLabel("Por: Mateus Silva Pereira - (24.00511-8)");

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
}
