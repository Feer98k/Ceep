package com.example.ceep.classes.model;
import com.example.ceep.classes.constantes.general.coresEnum;
import com.example.ceep.classes.model.Cor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaDeCores {
    public static final List<Cor> listaCores = new ArrayList<>(Arrays.asList(
            new Cor(coresEnum.BRANCO),
            new Cor(coresEnum.AZUL),
            new Cor(coresEnum.VERMELHO),
            new Cor(coresEnum.VERDE),
            new Cor(coresEnum.AMARELO),
            new Cor(coresEnum.LILAS),
            new Cor(coresEnum.CINZA),
            new Cor(coresEnum.MARROM),
            new Cor(coresEnum.ROXO)));

    public final List<Cor> todos() {
        return new ArrayList<>(listaCores);
    }
}
