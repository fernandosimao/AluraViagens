package br.com.alura.aluraviagens.util;

import android.support.annotation.NonNull;

public class DiasUtil {

    public static final String PLURAL = " dias";
    public static final String SINGULAR = " dia";

    /*
    Como nossos dias de duração terá o formato "2 dias" (no plural) ou "1 dia" (no singular) chamamos em ResumoPacoteActivity a
    função auxiliar DiasUtil.formataEmTexto que faz esse trabalho
     */
    @NonNull
    public static String formataEmTexto(int quantidadeDeDias) {
        if(quantidadeDeDias > 1){
            return quantidadeDeDias + PLURAL;
        }
        return quantidadeDeDias + SINGULAR;
    }

}
