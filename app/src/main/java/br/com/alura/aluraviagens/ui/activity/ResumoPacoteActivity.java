package br.com.alura.aluraviagens.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.util.DataUtil;
import br.com.alura.aluraviagens.util.DiasUtil;
import br.com.alura.aluraviagens.util.MoedaUtil;
import br.com.alura.aluraviagens.util.ResourceUtil;

import static br.com.alura.aluraviagens.ui.activity.PacoteActivityConstantes.CHAVE_PACOTE;


public class ResumoPacoteActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Resumo do pacote";

    /*
    Chegamos aqui através de um clique realizado em um item da lista presente na ListaPAcotesActivity. Inicialmente setamos o
    layout e o título da APPBAR, depois chamamos o método carregaPacoteRecebido(); que vai, dentre outras coisas, buscar o pacote
    enviado via extra de ListaPacotesActivity
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_pacote);
        setTitle(TITULO_APPBAR);
        carregaPacoteRecebido();
    }

    /*
    Esse método busca o pacote enviado via extra de ListaPacotesActivity. De posse do Pacote, fazemos a inicialização dos campos
    através do método inicializaCampos(Pacote pacote), método que vai chamar uma série de outros (local, imagem, dias, preço e
    data) que farão a vinculação do campo criado em nosso layout com uma variável criada, que depois será preenchida com as
    informações do nosso pacote
     */
    private void carregaPacoteRecebido() {
        Intent intent = getIntent();
        if(intent.hasExtra(CHAVE_PACOTE)){
            final Pacote pacote = (Pacote) intent.getSerializableExtra(CHAVE_PACOTE);

            inicializaCampos(pacote);
            configuraBotao(pacote);
        }
    }

    /*
    Aqui configuramos o clique do botão que nos levará para a próxima activity, PagamentoActivity. Após fazer o vínculo
    da variável botaoRealizaPagamento com o botão criado em nosso layout, setamos que o clique nos leverá para a função
     vaiParaPagamento(pacote)
     */
    private void configuraBotao(final Pacote pacote) {
        Button botaoRealizaPagamento = findViewById(R.id.resumo_pacote_botao_realiza_pagamento);
        botaoRealizaPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaiParaPagamento(pacote);
            }
        });
    }

    /*
    Essa função nos envia para a próxima activity PagamentoActivity colocando um extra no envio, o pacote que foi clicado
    na ListaActivity e foi exibido aqui em ResumoPacoteActivity,
     */
    private void vaiParaPagamento(Pacote pacote) {
        Intent intent = new Intent(ResumoPacoteActivity.this,
                PagamentoActivity.class);
        intent.putExtra(CHAVE_PACOTE, pacote);
        startActivity(intent);
    }

    /*
    Esse método chama diversos outros que farão a vinculação do campo criado em nosso layout com uma variável criada, q
    ue depois será preenchida com as informações do nosso pacote
     */
    private void inicializaCampos(Pacote pacote) {
        mostraLocal(pacote);
        mostraImagem(pacote);
        mostraDias(pacote);
        mostraPreco(pacote);
        mostraData(pacote);
    }

    /*
   Esse método faz a vinculação do campo criado em nosso layout com uma variável criada, que depois será preenchida
    com as informações do nosso pacote. Como nossa data terá o formato "18/04 - -20/04 de 2020" chamamos a função auxiliar
    DataUtil.periodoEmTexto que faz esse trabalho. Finalmente, como o dado formatado, fazemos o preenchimmento da nossa variavel
    data criada anteriormente.
    */
    private void mostraData(Pacote pacote) {
        TextView data = findViewById(R.id.resumo_pacote_data);
        String dataFormatadaDaViagem = DataUtil.periodoEmTexto(pacote.getDias());
        data.setText(dataFormatadaDaViagem);
    }

    /*
   Esse método faz a vinculação do campo criado em nosso layout com uma variável criada, que depois será preenchida
   com as informações do nosso pacote. Como nossa moeda terá o formato "R$ 243,99" chamamos a função auxiliar
   MoedaUtil.formataParaBrasileiro que faz esse trabalho. Finalmente, como o dado formatado, fazemos o preenchimmento da
   nossa variavel preco criada anteriormente.
    */
    private void mostraPreco(Pacote pacote) {
        TextView preco = findViewById(R.id.resumo_pacote_preco);
        String moedaBrasileira = MoedaUtil
                .formataParaBrasileiro(pacote.getPreco());
        preco.setText(moedaBrasileira);
    }

    /*
   Esse método faz a vinculação do campo criado em nosso layout com uma variável criada, que depois será preenchida
   com as informações do nosso pacote. Como nossos dias de duração terá o formato "2 dias" (no plural) ou "1 dia" (no singular)
   chamamos a função auxiliar DiasUtil.formataEmTexto que faz esse trabalho. Finalmente, como o dado formatado, fazemos
   o preenchimmento da nossa variavel dias criada anteriormente.
    */
    private void mostraDias(Pacote pacote) {
        TextView dias = findViewById(R.id.resumo_pacote_dias);
        String diasEmTexto = DiasUtil.formataEmTexto(pacote.getDias());
        dias.setText(diasEmTexto);
    }

    /*
   Esse método faz a vinculação do campo criado em nosso layout com uma variável criada, que depois será preenchida
   com as informações do nosso pacote. Como nossos imagens estão no diretorio res/drawable, precisamos acessar elas usando
   um nome, uma string, e para isso chamamos a função auxiliar ResourceUtil.devolveDrawable que faz esse trabalho. Finalmente,
   com a imagem obtida, fazemos o preenchimmento da nossa variavel imagem obtida anteriormente.
    */
    private void mostraImagem(Pacote pacote) {
        ImageView imagem = findViewById(R.id.resumo_pacote_imagem);
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
        TextView local = findViewById(R.id.resumo_pacote_local);
        local.setText(pacote.getLocal());
    }
}
