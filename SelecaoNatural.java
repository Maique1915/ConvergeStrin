/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convergestring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author maique
 */
public class SelecaoNatural {
    
    protected ArrayList<String> selecionados;//array auxiliar. usado para inserir melhores em cada geracao
    protected final int QTD_IND = 200;//qtd de individuos dentro da populacao
    protected ArrayList<String> Individuo;//lista de individuos de cada geracao
    protected final int QTD_GER = 80000;//qtd de geracoes
    protected Random rand = new Random();
    protected final int QTD_SEL = 7;//qtd de selecionados em cada geracao
    private final int TAM_STR;//tamaho do individuo base
    private final String STR;//individuo base
    private final String[] GENES = {"Q","W","E","R","T","Y","U","I","O","P","A",
        "S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M","q","w","e",
        "r","t","y","u","i","o","p","a","s","d","f","g","h","j","k","l","z","x",
        "c","v","b","n","m",":",";","<",">"," ",",",".","?","/","!","1","2","3",
        "4","5","6","7","8","9","0","@","#","$","%","^","&","*","(",")","-","_",
        "+","="};
    
    public SelecaoNatural(String STR) {
        TAM_STR = STR.length();
        this.STR = STR;
        
        Individuo = new ArrayList<String>();
        int i=0,d =0;
        
        populacaoInicial();
        while(maisAdaptado(d)!=TAM_STR){
            seleciona();
            crossover();
            multacao();
            if(i >=QTD_GER){
                break;
            }
            d++;
            i++;
        }
    }
    
    private void populacaoInicial(){//gera a populacao inicial
        for (int J=0;J<QTD_IND;J++){
            String aux ="";
            for (int H=0;H<TAM_STR;H++){
                aux += GENES[rand.nextInt(GENES.length)];
            }  
            Individuo.add(aux);
        }
        
    }
    
    protected void seleciona(){//seleciona melhores da populacao
        selecionados = new ArrayList<>();
        Map<String, Integer> aux = new HashMap<>();
        for (int J=0;J<QTD_IND;J++){
            if(!aux.containsKey(Individuo.get(J)) && avalia(Individuo.get(J)) > 0){
                aux.put(Individuo.get(J), avalia(Individuo.get(J)));
            }
        }
        aux.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(QTD_SEL).forEach((o) -> selecionados.add(o.getKey()));
      }
    
    protected void multacao(){//muda aleatoriamente algum caracter.
        int indice = rand.nextInt(QTD_IND);
        //int indice = QTD_IND-2;
        String ind = Individuo.get(indice);
        String[] aux = ind.split("");
        
        aux[rand.nextInt(TAM_STR)] = GENES[rand.nextInt(GENES.length)];
        
        ind = "";
        for (int i = 0; i < aux.length; i++) {
            ind += aux[i];
        }
        Individuo.set(indice, ind);

    }
    
    protected void crossover(){//cruza indiviuos aleatoriamente

        ArrayList<String> filhos = new ArrayList<>();
        String pai = null;
        String mae = null;
        for (int i = 0; i < QTD_IND; i++) {
            int corte = 0;
            do{
                pai = selecionados.get(rand.nextInt(QTD_SEL-1));
                mae = selecionados.get(rand.nextInt(QTD_SEL-1));
            }while(pai.equals(mae)|| pai.isEmpty()|| mae.isEmpty());
  
            do{
                corte = rand.nextInt(TAM_STR);
            }while(corte == 0 || corte == TAM_STR);
            
            filhos.add(pai.substring(0, corte)+mae.substring(corte, TAM_STR));
            filhos.add(mae.substring(0, corte)+pai.substring(corte, TAM_STR));

        }
        Individuo = filhos;
    }
   
    private int maisAdaptado(int i){
        int max=-1,I = -1;
        double p = 0;
        for(int K=0;K<Individuo.size();K++){
            if (avalia(Individuo.get(K))>max){
                max=avalia(Individuo.get(K));
                I=K;
            }
        }
        p = (Double.valueOf(max)/TAM_STR)*100;
        System.out.printf("Geracao: %d - Igual ao original: %.2f %%  - Melhor individuo: %s \n",i,p,Individuo.get(I));
        
        return max;//retorna a Nota da melhor String.
    }
     
    private int avalia(String F){
        int G=0;

        for (int H = 0;H< TAM_STR;H++){//verifica se na String criada ha carateres iguais e na mesma posicao que a da String base
            if (F != null){
                if (STR.charAt(H) == F.charAt(H)) {
                    G++;
                }
            }
        }
        return G;//Retorna A Nota Do Individuo.
    }

}
