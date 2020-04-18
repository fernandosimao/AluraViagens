package br.com.alura.aluraviagens.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.util.DataUtil;
import br.com.alura.aluraviagens.util.MoedaUtil;
import br.com.alura.aluraviagens.util.ResourceUtil;

import static br.com.alura.aluraviagens.ui.activity.PacoteActivityConstantes.CHAVE_PACOTE;

public class ResumoCompraActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Resumo da compra";

    /*
    Chegamos aqui através de um clique realizado no botão Finalizar Compra em PagamentoActivity. Inicialmente setamos o
    layout e o título da APPBAR, depois chamamos o método carregaPacoteRecebido(); que vai, dentre outras coisas, buscar o pacote
    enviado via extra de PagamentoActivity
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_compra);
        setTitle(TITULO_APPBAR);
        carregaPacoteRecebido();
    }

    /*
  Esse método busca o pacote enviado via extra de PagamentoActivity. De posse do Pacote, fazemos a inicialização do campos
  preço através do método inicializaCampos(pacote);
   */
    private void carregaPacoteRecebido() {
        Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_PACOTE)){
            Pacote pacote = (Pacote) intent.getSerializableExtra(CHAVE_PACOTE);
            inicializaCampos(pacote);
        }
    }

    /*
    Esse método chama diversos outros que farão a vinculação do campo criado em nosso layout com uma variável criada, q
    ue depois será preenchida com as informações do nosso pacote
     */
    private void inicializaCampos(Pacote pacote) {
        mostraLocal(pacote);
        mostraImagem(pacote);
        mostraData(pacote);
        mostraPreco(pacote);
    }

    /*
  Esse método faz a vinculação do campo criado em nosso layout com uma variável criada, que depois será preenchida
  com as informações do nosso pacote. Como nossa moeda terá o formato "R$ 243,99" chamamos a função auxiliar
  MoedaUtil.formataParaBrasileiro que faz esse trabalho. Finalmente, como o dado formatado, fazemos o preenchimmento da
  nossa variavel preco criada anteriormente.
   */
    private void mostraPreco(Pacote pacote) {
        TextView preco = findViewById(R.id.resumo_compra_preco_pacote);
        String moedaBrasileira = MoedaUtil
                .formataParaBrasileiro(pacote.getPreco());
        preco.setText(moedaBrasileira);
    }

    /*
  Esse método faz a vinculação do campo criado em nosso layout com uma variável criada, que depois será preenchida
   com as informações do nosso pacote. Como nossa data terá o formato "18/04 - -20/04 de 2020" chamamos a função auxiliar
   DataUtil.periodoEmTexto que faz esse trabalho. Finalmente, como o dado formatado, fazemos o preenchimmento da nossa variavel
   data criada anteriormente.
   */
    private void mostraData(Pacote pacote) {
        TextView data = findViewById(R.id.resumo_compra_data_viagem);
        String periodoEmTexto = DataUtil
                .periodoEmTexto(pacote.getDias());
        data.setText(periodoEmTexto);
    }

    /*
  Esse método faz a vinculação do campo criado em nosso layout com uma variável criada, que depois será preenchida
  com as informações do nosso pacote. Como nossos imagens estão no diretorio res/drawable, precisamos acessar elas usando
  um nome, uma string, e para isso chamamos a função auxiliar ResourceUtil.devolveDrawable que faz esse trabalho. Finalmente,
  com a imagem obtida, fazemos o preenchimmento da nossa variavel imagem obtida anteriormente.
   */
    private void mostraImagem(Pacote pacote) {
        ImageView imagem = findViewById(R.id.resumo_compra_imagem_pacote);
        Drawable drawableDoPacote = ResourceUtil
                .devolveDrawable(this, pacote.getImagem());
        imagem.setImageDrawable(drawableDoPacote);
    }

    /*
  Esse método faz a vinculação do campo criado em nosso layout com uma variável criada, que depois será preenchida
  com as informações do nosso pacote. Como esse informação já está pronta, fazemos o preenchimmento da nossa variavel local
  diretamente, como o método .setText.
   */
    private void mostraLocal(Pacote pacote) {
        TextView local = findViewById(R.id.resumo_compra_local_pacote);
        local.setText(pacote.getLocal());
    }
}
