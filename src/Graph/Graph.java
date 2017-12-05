package Graph;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*  Input: Graph data stored in a txt file with the following format e.g data.txt
    *  13 vertices, 13 edges
    *  0: 6 2 1 5 
    *  1: 0 
    *  2: 0 
    *  3: 5 4 
    *  4: 5 6 3 
    *  5: 3 4 0 
    *  6: 0 4 
    *  7: 8 
    *  8: 7 
    *  9: 11 10 12 
    *  10: 9 
    *  11: 9 12 
    *  12: 11 9 
 */
public class Graph {

    private final List<Vertex> vertices;
    private Arquivos a = new Arquivos();
    
    String cor(int cor){
        switch (cor) {
            case 0:
                return "blue";
            case 1:
                return "yellow";
            case 2:
                return "red";
            case 3:
                return "green";
            case 4:
                return "white";
            case 5:
                return "gray";
            case 6:
                return "orange";
            default:
                return "purple";
        }
    }
    
    public void preencher_linhas_iniciais(String linha){
        a.escritor("C:\\Users\\Fernando\\Documents\\5_periodo\\graph.dot", linha);
    }
    public void preencher_linhas_de_verticies(String arquivo) throws IOException{
        a.leitor("C:\\Users\\Fernando\\Documents\\NetBeansProjects\\Graph\\src\\Graph\\" + arquivo);
    }

    public Graph(String _path) {
        vertices = new ArrayList<>();
        ArrayList<String> lines = (ArrayList<String>) readGraphData(_path);

        if (lines != null) {
            for (int i = 1; i < lines.size(); i++) {
                String node = lines.get(i).split(":")[0];
                String[] adj = lines.get(i).split(":")[1].split(" ");

                List<String> neighbors = new ArrayList<>();
                for (int j = 1; j < adj.length; j++) {
                    neighbors.add(adj[j]);
                }
                vertices.add(new Vertex(node, new ArrayList<>(neighbors)));
            }
        }
    }

    @Override
    public String toString() {
        String result = "";
        result = vertices.stream().map((v) -> v.node + ":" + v.neighbors.toString() + "\n").reduce(result, String::concat);
        return result;
    }

    /*
     * Implementation of the Welsh-Powell Algorithm
     */
    
    public void colourVertices() {
        Collections.sort(vertices, new VertexComparator()); // arrange vertices in order of descending valence
        Map<String, String> vertex_color_index = new HashMap<>(); //create Map<Vertex, Color>
        for (int i = 0; i < vertices.size(); i++) {
            if ((vertex_color_index.containsKey(vertices.get(i).node))) {
            } else {
                vertex_color_index.put(vertices.get(i).node, "Cor " + i + "\n"); //color first vertex in list with color 1
                
                //AQUI VAI DEFINIR AS CORES DOS VERTICES
                preencher_linhas_iniciais("\t\t"+vertices.get(i).node + " [fillcolor="+cor(i)+" fixedsize=true]");
                
                for (int j = i + 1; j < vertices.size(); j++) {
                    if (!(vertices.get(i).neighbors.contains(vertices.get(j).node)) && !(vertex_color_index.containsKey(vertices.get(j).node))) {
                        vertex_color_index.put(vertices.get(j).node, "Cor " + i + "\n");
                        
                        //AQUI VAI DEFINIR AS CORES DOS VERTICES
                        preencher_linhas_iniciais("\t\t"+vertices.get(j).node + " [fillcolor="+cor(i)+" fixedsize=true]");
                    }
                }
            }
        }
        System.out.println(vertex_color_index);

    }

    private List<String> readGraphData(String _path) {
        Path path = FileSystems.getDefault().getPath(_path, "");
        try {
            return Files.readAllLines(path, Charset.defaultCharset());
        } catch (IOException e) {
            System.err.println("I/O Error");
            System.out.println(e);
            return null;
        }
    }

    private static class Vertex {

        private String node;
        private List<String> neighbors;

        public Vertex(String node, ArrayList<String> neighbors) {
            this.node = node;
            this.neighbors = neighbors;
        }

    }

    class VertexComparator implements Comparator<Vertex> {

        @Override
        public int compare(Vertex a, Vertex b) {
            return a.neighbors.size() < b.neighbors.size() ? 1 : a.neighbors.size() == b.neighbors.size() ? 0 : -1;
        }

    }
    
}

