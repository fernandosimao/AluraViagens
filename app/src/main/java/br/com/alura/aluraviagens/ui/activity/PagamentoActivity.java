package br.com.alura.aluraviagens.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.util.MoedaUtil;

import static br.com.alura.aluraviagens.ui.activity.PacoteActivityConstantes.CHAVE_PACOTE;

public class PagamentoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Pagamento";

    /*
    Chegamos aqui através de um clique realizado no botão Realizar Pagamento em ResumoPacoteActivity. Inicialmente setamos o
    layout e o título da APPBAR, depois chamamos o método carregaPacoteRecebido(); que vai, dentre outras coisas, buscar o pacote
    enviado via extra de ResumoPacoteActivity
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        setTitle(TITULO_APPBAR);
        carregaPacoteRecebido();
    }

    /*
   Esse método busca o pacote enviado via extra de ResumoPacoteActivity. De posse do Pacote, fazemos a inicialização do campo
   preço através do método mostraPreco(pacote). Finalmente chamamos o método configuraBotao(pacote)que nos levará para a próxima
   activity ao ser clicado.
    */
    private void carregaPacoteRecebido() {
        Intent intent = getIntent();
        if(intent.hasExtra(CHAVE_PACOTE)){
            final Pacote pacote = (Pacote) intent.getSerializableExtra(CHAVE_PACOTE);
            mostraPreco(pacote);
            configuraBotao(pacote);
        }
    }

    /*
    Aqui configuramos o clique do botão que nos levará para a próxima activity, ResumoCompraActivity. Após fazer o vínculo
    da variável botaoFinalizaCompra com o botão criado em nosso layout, setamos que o clique nos leverá para a função
     vaiParaResumoCompra(pacote)
     */
    private void configuraBotao(final Pacote pacote) {
        Button botaoFinalizaCompra = findViewById(R.id.pagamento_botao_finaliza_compra);
        botaoFinalizaCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaiParaResumoCompra(pacote);
            }
        });
    }

    /*
    Essa função nos envia para a próxima activity ResumoCompraActivity colocando um extra no envio, o pacote que foi clicado
    na ListaActivity, foi exibido em ResumoPacoteActivity e também aqui na tela de pagamento (o preço), PagamentoActivity.
     */
    private void vaiParaResumoCompra(Pacote pacote) {
        Intent intent = new Intent(PagamentoActivity.this,
                ResumoCompraActivity.class);
        intent.putExtra(CHAVE_PACOTE, pacote);
        startActivity(intent);
    }

    /*
   Esse método faz a vinculação do campo criado em nosso layout com uma variável criada, que depois será preenchida
   com as informações do nosso pacote. Como nossa moeda terá o formato "R$ 243,99" chamamos a função auxiliar
   MoedaUtil.formataParaBrasileiro que faz esse trabalho. Finalmente, como o dado formatado, fazemos o preenchimmento da
   nossa variavel preco criada anteriormente.
    */
    private void mostraPreco(Pacote pacote) {
        TextView preco = findViewById(R.id.pagamento_preco_pacote);
        String moedaBrasileira = MoedaUtil
                .formataParaBrasileiro(pacote.getPreco());
        preco.setText(moedaBrasileira);
    }
}
