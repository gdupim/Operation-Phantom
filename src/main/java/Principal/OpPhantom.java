package Principal;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;

public class OpPhantom {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // SQL
        Connection conn = ConexaoJDBC.getConexao();

        // Janela do jogo
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("opPhantom");

        Janela janela = new Janela();
        frame.add(janela);

        frame.pack();

        // esse comentario é só pro github reconhecer q estou fazendo um commit pq ele é
        // tonto e n me deixa fazer as coisas - gabriel
        // frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza a janela
        // frame.setUndecorated(true); // Remove bordas da janela
        frame.setVisible(true);

        janela.setupGame();
        janela.iniciarGameThread();

        // MYSQL
        UI ui = new UI(janela);
        double tempo = ui.playTime;
        Statement stmt = conn.createStatement();
        stmt.executeQuery("INSERT INTO tempoJogo VALUES (" + tempo + ");");

        conn.close();
    }
}