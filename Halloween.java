public class Halloween {

    public final class Node {
        private int doce; 
        private boolean jaPerc;
        Node antecessor, esquerda, direita;

        public Node(int doce, Node esquerda, Node direita, Node ant) {
            this.doce = doce;
            this.esquerda = esquerda;
            this.direita = direita;
            this.antecessor = ant;
        }
    }

    private int index;   
    private String line; 

    
    public void solve(String line) {
        this.index = 0;     
        this.line = line;  
        Node tree = parseTree(null);  
        int[] result;

        if (tree.esquerda == null && tree.direita == null) {
            
            int[] temp = {0, tree.doce};
            result = temp;

        } else {
           
            result = PercorrerEContar(tree);

        }
        
        System.out.println("Ruas percorridas: " + result[0] + "   " + " Doces ganhos: " + result[1]);
    }

    private Node parseTree(Node ant) {
        
        if (index >= line.length())
            
            throw new IllegalArgumentException("O índice não pode ser maior ou igual ao comprimento da linha.");

        while (line.charAt(index) == ' ')
            index++;

        if (line.charAt(index) == '(') {
            
            index++; 
            Node t = new Node(0, null, null, ant); 
            Node esquerda = parseTree(t);  
            Node direita = parseTree(t); 
            t.esquerda = esquerda;
            t.direita = direita;
            index++; 
           
            return t;

        } else {
           
            int start = index;
          
            while (index < line.length() && Character.isDigit(line.charAt(index)))
                index++;
            int end = index;
          
            return new Node(Integer.parseInt(line.substring(start, end)), null, null, ant);
        }
    }

    // método que percorre a árvore e conta os doces
    public int[] PercorrerEContar(Node raiz) {
        int[] quantidade = new int[2];

        // caso base
        if (raiz.direita == null && raiz.esquerda == null) {
            quantidade[0] = 0;
            quantidade[1] = raiz.doce;
            return quantidade;
        }

        int qtdFilhosDir = 0;
        int qtdFilhosEsq = 0;

        if (raiz.direita != null)
            qtdFilhosDir = Tamanho(raiz.direita);
        if (raiz.esquerda != null)
            qtdFilhosEsq = Tamanho(raiz.esquerda);

        if (qtdFilhosDir <= qtdFilhosEsq)
            quantidade = Contar(raiz.direita, raiz);
        else
            quantidade = Contar(raiz.esquerda, raiz);

        return quantidade;
    }

    // método que conta os doces em uma determinada direção
    public int[] Contar(Node atual, Node raiz) {
        int[] contagem = new int[2];
        int casasDiferentes = 0;
        ContarRecursivo(atual, contagem, casasDiferentes, raiz);
        return contagem;
    }

    // método RECURSIVO para percorrer a árvore e contar os doces
    private void ContarRecursivo(Node no, int[] contagem, int casasDiferentes, Node raiz) {
        if (no == null || casasDiferentes == Tamanho(raiz))
            return;

        if (!no.jaPerc)
            casasDiferentes++;

        contagem[0]++;
        contagem[1] += no.doce;
        no.jaPerc = true;

        int qtdDir = Tamanho(no.direita);
        int qtdEsq = Tamanho(no.esquerda);

        if (qtdDir <= qtdEsq) {
            if (no.direita != null && !no.direita.jaPerc)
                ContarRecursivo(no.direita, contagem, casasDiferentes, raiz);
            else if (no.esquerda != null && !no.esquerda.jaPerc)
                ContarRecursivo(no.esquerda, contagem, casasDiferentes, raiz);
            else
                ContarRecursivo(no.antecessor, contagem, casasDiferentes, raiz);
        } else {
            if (no.esquerda != null && !no.esquerda.jaPerc)
                ContarRecursivo(no.esquerda, contagem, casasDiferentes, raiz);
            else if (no.direita != null && !no.direita.jaPerc)
                ContarRecursivo(no.direita, contagem, casasDiferentes, raiz);
            else
                ContarRecursivo(no.antecessor, contagem, casasDiferentes, raiz);
        }
    }

    // método para calcular o tamanho de uma subárvore
    public int Tamanho(Node current) {
        if (current == null)
            return 0;

        return 1 + (Tamanho(current.direita) + Tamanho(current.esquerda));
    }
}
