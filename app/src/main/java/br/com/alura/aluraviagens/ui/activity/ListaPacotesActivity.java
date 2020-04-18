package br.com.alura.aluraviagens.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.dao.PacoteDAO;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.ui.adapter.ListaPacotesAdapter;

import static br.com.alura.aluraviagens.ui.activity.PacoteActivityConstantes.CHAVE_PACOTE;

public class ListaPacotesActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Pacotes";

    /*
    Primeiramente setamos layout da activity e o título da APPBAR e depois configuramos a lista com as ofertas através do método
    configuraLista();
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacotes);
        setTitle(TITULO_APPBAR);
        configuraLista();
    }

    /*
    Esse método faz o vinculo do listview (lista_pacotes_listview) criado em nosso menu (activity_lista_pacotes.xml) para uma
    variável do tipo ListView listaDePacotes, depois, através do nosso DAO, pegamos toda a lista presente nele e atribuímpos
    a variável pacotes. Isso porque para criar nosso adapter, através do método setAdapter, precisamos passar uma ListaPacotesAdapter
    e também nossa lista de pacotes. Ao contrário do RecyclerView, o ListView tem um método que permite setarmos o comportamento
    para o clique em um item de nossa lista, o .setOnItemClickListener. Com esse método conseguimos identificar o pacote clicado
    através do argumento posicao do OnItemClick através do .get na lista. Finalmente levamos o usuário que clicar no item para
    outra activity através do método vaiParaResumoPacote(Pacote pacoteClicado)
     */
    private void configuraLista() {
        ListView listaDePacotes = findViewById(R.id.lista_pacotes_listview);
        final List<Pacote> pacotes = new PacoteDAO().lista();
        listaDePacotes.setAdapter(new ListaPacotesAdapter(pacotes, this));
        listaDePacotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Pacote pacoteClicado = pacotes.get(posicao);
                vaiParaResumoPacote(pacoteClicado);
            }
        });
    }

    /*
    Aqui levamos o usuário que clicar no item para outra activity. Colocamos a chave que identifica o extra na constante
    CHAVE_PACOTE e enviamos nela um objeto do tipo Pacote que foi obtido através do clique em um item da lista
     Pacote pacoteClicado = pacotes.get(posicao);
     */
    private void vaiParaResumoPacote(Pacote pacoteClicado) {
        Intent intent = new Intent(ListaPacotesActivity.this,
                ResumoPacoteActivity.class);
        intent.putExtra(CHAVE_PACOTE, pacoteClicado);
        startActivity(intent);
    }
}
