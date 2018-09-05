package br.com.senaijandira.jogodaforca;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by 17259195 on 13/08/2018.
 */

public class MainActivty extends Activity {
    TextView txtPalavra;
    TextView txtCategoria;
    TextView txtErros;
    TextView txtAcertos;
    String palavra = "";

    static final  String temas[] = {
            "ANIMAL",
            "FRUTA",
            "PROFISSOES",
            "PAISES",
            "SERIES"
    };
    static final String[][] palavras = {{ //ANIMAIS
            "ARARA",
            "CACHORRO",
            "GATO",
            "TIGRE",
            "ELEFANTE",
            "ZEBRA",
            "AXALOTE",
            "CAMELO",
            "URUBU",
            "COBRA",
            "COCRODILO",
            "MACACO"
    },
            { //FRUTAS

            "LARANJA",
            "MARACUJA",
            "UVA",
            "KIWI",
            "CARAMBOLA",
            "CEREJA",
            "MORANGO",
            "PESSEGO",
            "MANGA",
            "JABUTICABA",
            "AMEIXA"
         },


            {//PROFISSOES
                    "FOTOGRAFO",
                    "MEDICO",
                    "PINTOR",
                    "JARDINEIRO",
                    "PROGRAMADOR",
                    "ATOR",
                    "ESTETISISTA",
                    "ADVOGADO",
                    "PROFESSOR",
                    "JORNALISTA",
                    "ENGENHEIRO"

            },

            {//PAISES
                    "BRASIL",
                    "MEXICO",
                    "PORTUGAL",
                    "ALEMANHA",
                    "JAPAO",
                    "RUSSIA",
                    "ARGENTINA",
                    "ANGOLA",
                    "BAHAMAS",
                    "BELIZE",
                    "BOTSWANA"

            },

            {//SERIES
                    "RIVERDALE",
                    "VIKINGS",
                    "OZARK",
                    "DARK",
                    "LUCIFER",
                    "NARCOS",
                    "ATYPICAL",
                    "GLEE",
                    "LOVE",
                    "MARCO POLO"

            }
    };


    static final int VITORIA = 0;
    static final int DERROTA = 1;

//    Palavra escolhida e categoria
    int numTema = temaSorteado();
    String temaEscolhido = temas[numTema];
    String palavraEscolhida = sorteioPalavra(numTema);

//    Acertos e erros
    int Tacertos = palavraEscolhida.length();
    int Terros = 5;

    int qntdErros = 0;
    int qntdAcertos = 0;


//    Iniciando a tela
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//       Itens que estao no xml
        txtPalavra = findViewById(R.id.txtPalavra);
        txtErros = findViewById(R.id.txtErros);
        txtAcertos = findViewById(R.id.txtAcertos);
        txtCategoria = findViewById(R.id.txtCategoria);

//        Printar na tela a categoria sorteada
        txtCategoria.setText(temaEscolhido);

//        Printar na tela os _ de acordo com o tamanho da palavra sorteada
        for(int i=0; i <= palavraEscolhida.length() - 1; i++){
            if(i == palavraEscolhida.length() - 1){
                palavra += "_";
            }else{
                palavra += "_ ";
            }
        }

        txtPalavra.setText(palavra);

//        Contator de erros e acertos
        atualizarAcertos();
        atualizarErros();
    }

//    Click do botão com as letras
    public void clickBotao(View v) {
        char letra = v.getTag().toString().charAt(0);
        v.setEnabled(false);

        boolean erro = true;

        for(int i = 0; i <= palavraEscolhida.length() - 1; i++){
            if(letra == palavraEscolhida.charAt(i)){
                StringBuilder novaPalavra = new StringBuilder(palavra);
                novaPalavra.setCharAt(i * 2, letra);
                palavra = novaPalavra.toString();
                txtPalavra.setText(palavra);
                qntdAcertos += 1;
                erro = false;
            }
        }

//        Mudar a cor dos botoes de acordo com os erros ou acertos
        if(erro){
            v.setBackgroundColor(getResources().getColor(R.color.vermelho));
            qntdErros += 1;
            atualizarErros();
        }else{
            v.setBackgroundColor(getResources().getColor(R.color.verde));
            atualizarAcertos();
        }
        if(qntdErros >= Terros){
            gameOver(DERROTA);
        }else if(qntdAcertos >= Tacertos){
            gameOver(VITORIA);
        }
    }


    public void reiniciar(View v){
        recreate();
    }

//   Alert do fim de jogo
    private void gameOver(int vitoria){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setCancelable(false);

        if(vitoria == VITORIA){
            alert.setTitle("Você Ganhou \uD83D\uDE00");
            alert.setMessage("Você acertou a palavra. Parabéns.");
        }else{
            alert.setTitle("Você Perdeu \uD83D\uDE22");
            alert.setMessage("Você errou a palavra. A palavra é '" + palavraEscolhida + "'.");
        }
        alert.setNegativeButton("sair", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alert.setPositiveButton("reiniciar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                recreate();
            }
        });

        alert.create().show();
    }

    private void atualizarAcertos(){
        txtAcertos.setText("Acertos: " + qntdAcertos);
    }

    private void atualizarErros(){
        txtErros.setText("Erros: " + qntdErros + "/" + Terros);
    }


//    Sorteio da categoria e palavra
    private int temaSorteado(){
        int min = 0;
        int max = temas.length - 1;

        return new Random().nextInt((max - min) + 1) + min;
    }
    private String sorteioPalavra(int numeroCategoria){
        int min = 0;
        int max = palavras[numeroCategoria].length - 1;
        final int numeroPalavra = new Random().nextInt((max - min) + 1) + min;

        return palavras[numeroCategoria][numeroPalavra];
    }
}
