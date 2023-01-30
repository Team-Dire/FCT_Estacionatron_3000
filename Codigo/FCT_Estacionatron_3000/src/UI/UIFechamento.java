package UI;

import Classes.ControladorEstacionamento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIFechamento extends JFrame{
    private JPanel panelMain;
    private JButton btnFechar;
    private JTextArea textVeiculos;
    private JLabel labelTitle;
    private ControladorEstacionamento controladorEstacionamento;
    public UIFechamento(ControladorEstacionamento controladorEstacionamento){
        this.controladorEstacionamento = controladorEstacionamento;
        this.setContentPane(this.panelMain);
        this.setTitle("FCT Estacionatron 3000");
        this.setSize(500, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        btnFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textVeiculos.setText(controladorEstacionamento.fecharEstacionamento());
            }
        });
    }
}
