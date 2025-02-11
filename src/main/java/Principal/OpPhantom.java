package Principal;

import javax.swing.JFrame;

public class OpPhantom {
    public static void main(String[] args) throws  Exception{
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("opPhantom");

        Janela janela = new Janela();
        frame.add(janela);

        frame.pack();

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true); // Remove bordas da janela
        frame.setVisible(true);

        janela.iniciarGameThread();
        
    }
}
