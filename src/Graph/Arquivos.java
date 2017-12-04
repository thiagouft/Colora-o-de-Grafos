package Graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class Arquivos {

    public void leitor(String path) throws IOException {
        try (BufferedReader buffRead = new BufferedReader(new FileReader(path))) {
            String linha = "";
            while (true) {
                if (linha != null) {
                    System.out.println(linha);
                    
                } else
                    break;
                linha = buffRead.readLine();
            }
        }
    }
    
    public void escritor(String path) throws IOException {
        try (BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path))) {
            File f = new File(path);
            FileWriter fw = new FileWriter(f,true);
            
            //String linha = "digraph G {\n";
            //buffWrite.append(linha + "\n");
            //linha = "[\n";
            //buffWrite.append(linha + "\n");
            //linha = "node [margin=0 fontcolor=black fontsize=32 width=0.5 shape=circle style=filled]";
            //buffWrite.append(linha + "\n");
        }
    }
    
    public void escritor(String path, String linha){
        try {
            File f = new File(path);
            FileWriter fw = new FileWriter(f,true);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(linha + "\n");
                bw.flush();
            }
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, "ERRO AO ESCREVER NO ARQUIVO:" + ex);
        }
    }
}
