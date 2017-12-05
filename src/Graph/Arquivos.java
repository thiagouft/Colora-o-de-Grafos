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
            
            String linha = ""; //LINHA QUE SERÁ LIDA NO ARQUIVO DATA.TXT
            char aux; 
            int x = 0; // UTILIZADO PARA IGNORAR A PRIMEIRA LINHA
            String escrever = "\t"; //STRING (MANIPULADA) QUE SERÁ REALMENTE ESCRITA NO ARQUIVO GRAPH.TXT

            linha = buffRead.readLine();
            
            while (true) {
                if (linha != null) {
                    //linha = buffRead.readLine();
                    
                    if(x != 0){
                        for(int i = 0; i < linha.length(); i++){
                            aux = linha.toCharArray()[i];
                            
                            if(aux == ':'){
                                escrever+= " -> {";
                            }
                            else if(i == linha.length() - 1){
                                escrever += " }\n";
                                escrever += "\t";
                            }
                            else{
                                escrever += aux;
                            }
                        }
                        
                    }
                    x++;
                    
                } else
                    break;
                linha = buffRead.readLine();
                
            }
            //escrever += '}';
            escritor("C:\\Users\\Fernando\\Documents\\5_periodo\\graph.dot", escrever);
            //escrever = "";
        }
        
    }
    
    public void escritor(String path, String linha){
        try {
            File f = new File(path);
            FileWriter fw = new FileWriter(f,true);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(linha);
                bw.newLine();
                bw.flush();
                bw.close();
                //fw.close();
            }
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, "ERRO AO ESCREVER NO ARQUIVO:" + ex);
        }
    }
    
    /*
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
    */
}
