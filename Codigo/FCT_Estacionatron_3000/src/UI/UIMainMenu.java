package UI;

import Classes.ControladorEstacionamento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIMainMenu extends JFrame {
    private final ControladorEstacionamento controladorEstacionamento = new ControladorEstacionamento();
    private JPanel panelMain;
    private JButton btnNovaEstadia;
    private JButton btnPagar;

    public UIMainMenu() {
        btnNovaEstadia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIEstadia uiEstadia = new UIEstadia(controladorEstacionamento);
            }
        });
        btnPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIPagamento uiPagamento = new UIPagamento(controladorEstacionamento);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public static void main(String[] args) {
        //ControladorEstacionamento controladorEstacionamento1 = new ControladorEstacionamento();
        UIMainMenu uiMainMenu = new UIMainMenu();
        uiMainMenu.setContentPane(uiMainMenu.panelMain);
        uiMainMenu.setTitle("FCT Estacionatron 3000");
        uiMainMenu.setSize(350, 600);
        uiMainMenu.setVisible(true);
        uiMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
