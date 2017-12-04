package Graph;

//import java.util.Scanner;

public class main {
    
    public static void main(String[] args) {
        //String t = "data.txt";
        String t1 = "data1.txt";
        //String t1;
        //Scanner s = new Scanner(System.in);
        //t1 = s.nextLine();
        
        Graph graph = new Graph("C:\\Users\\Fernando\\Documents\\NetBeansProjects\\Graph\\src\\Graph\\" + t1);
        graph.preencher_linhas_iniciais("digraph G {");
        graph.preencher_linhas_iniciais("{");
        graph.preencher_linhas_iniciais("node [margin=0 fontcolor=black fontsize=32 width=0.5 shape=circle style=filled]");
        graph.colourVertices();
        graph.preencher_linhas_iniciais("}");
    }
    
}
