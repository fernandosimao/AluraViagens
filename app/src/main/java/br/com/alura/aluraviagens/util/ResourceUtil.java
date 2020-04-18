package br.com.alura.aluraviagens.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class ResourceUtil {

    public static final String DRAWABLE = "drawable";


    /*
    Como nossos imagens estão no diretorio res/drawable, precisamos acessar elas usando um nome, uma string, e para isso chamamos,
    em ResumoPacoteActivity, a função auxiliar ResourceUtil.devolveDrawable que faz esse trabalho.
     */
    public static Drawable devolveDrawable(Context context, String drawableEmTexto) {
        Resources resources = context.getResources();
        int idDoDrawable = resources.getIdentifier(drawableEmTexto
                , DRAWABLE, context.getPackageName());
        return resources.getDrawable(idDoDrawable);
    }
}
