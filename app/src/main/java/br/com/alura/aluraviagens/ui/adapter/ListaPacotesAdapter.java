package br.com.alura.aluraviagens.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.alura.aluraviagens.util.DiasUtil;
import br.com.alura.aluraviagens.util.MoedaUtil;
import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.util.ResourceUtil;
import br.com.alura.aluraviagens.model.Pacote;

public class ListaPacotesAdapter extends BaseAdapter {

    private final List<Pacote> pacotes;
    private final Context context;

    /*
    Esse é o adapter que configuraremos no nosso ListView. Precisamos de uma lista e de um contexto. Na ListaPacotesActivity
    criamos um ListView chamado listaDePacotes e depois setamos o adapter nele chamando o construtor abaixo
    listaDePacotes.setAdapter(new ListaPacotesAdapter(pacotes, this));
     */
    public ListaPacotesAdapter(List<Pacote> pacotes, Context context){
        this.pacotes = pacotes;
        this.context = context;
    }

    /*
    O adapter do ListView que extende a classe BaseAdapter exige que implementemos 4 métodos: o getCount(), getItem(), o getItemId
    e o getView
     */

    //Método para identificar o tamanho da nossa lista
    @Override
    public int getCount() {
        return pacotes.size();
    }

    //Método para acessarmos determinados itens, dada sua posição
    @Override
    public Pacote getItem(int posicao) {
        return pacotes.get(posicao);
    }

    //Método para obter o id de cada item, caso tenhamos em nossa classe
    @Override
    public long getItemId(int i) {
        return 0;
    }

    /*Método onde fazemos o inflate (exibição na activity) da view de cada item (que pode ter várias views dentro dele
    - ViewGroup - no nosso caso item_pacote.xml
     */
    @Override
    public View getView(int posicao, View view, ViewGroup parent) {
        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.item_pacote, parent, false);

        Pacote pacote = pacotes.get(posicao);

        //pegamos o pacote, dada a posição acima, e preenchemos os campos no nosso layout item_pacote.xml usando as funções abaixo
        mostraLocal(viewCriada, pacote);
        mostraImagem(viewCriada, pacote);
        mostraDias(viewCriada, pacote);
        mostraPreco(viewCriada, pacote);

        return viewCriada;
    }

    /*
  Esse método faz a vinculação do campo criado em nosso layout com uma variável criada, que depois será preenchida
  com as informações do nosso pacote. Como nossa moeda terá o formato "R$ 243,99" chamamos a função auxiliar
  MoedaUtil.formataParaBrasileiro que faz esse trabalho. Finalmente, como o dado formatado, fazemos o preenchimmento da
  nossa variavel preco criada anteriormente.
   */
    private void mostraPreco(View viewCriada, Pacote pacote) {
        TextView preco = viewCriada.findViewById(R.id.item_pacote_preco);
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
    private void mostraDias(View viewCriada, Pacote pacote) {
        TextView dias = viewCriada.findViewById(R.id.item_pacote_dias);
        String diasEmTexto = DiasUtil.formataEmTexto(pacote.getDias());
        dias.setText(diasEmTexto);
    }

    /*
   Esse método faz a vinculação do campo criado em nosso layout com uma variável criada, que depois será preenchida
   com as informações do nosso pacote. Como nossos imagens estão no diretorio res/drawable, precisamos acessar elas usando
   um nome, uma string, e para isso chamamos a função auxiliar ResourceUtil.devolveDrawable que faz esse trabalho. Finalmente,
   com a imagem obtida, fazemos o preenchimmento da nossa variavel imagem obtida anteriormente.
    */
    private void mostraImagem(View viewCriada, Pacote pacote) {
        ImageView imagem = viewCriada.findViewById(R.id.item_pacote_imagem);
        Drawable drawableImagemPacote = ResourceUtil
                .devolveDrawable(context, pacote.getImagem());
        imagem.setImageDrawable(drawableImagemPacote);
    }

    /*
  Esse método faz a vinculação do campo criado em nosso layout com uma variável criada, que depois será preenchida
  com as informações do nosso pacote. Como esse informação já está pronta, fazemos o preenchimmento da nossa variavel local
  diretamente, como o método .setText.
   */
    private void mostraLocal(View viewCriada, Pacote pacote) {
        TextView local = viewCriada.findViewById(R.id.item_pacote_local);
        local.setText(pacote.getLocal());
    }

}
