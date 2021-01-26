package com.example.ceep.classes.model;
import com.example.ceep.classes.constants.general.ColorsEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaDeCores {
    public static final List<Cor> colorsList = new ArrayList<>(Arrays.asList(
            new Cor(ColorsEnum.WHITE),
            new Cor(ColorsEnum.BLUE),
            new Cor(ColorsEnum.RED),
            new Cor(ColorsEnum.GREEN),
            new Cor(ColorsEnum.YELLOW),
            new Cor(ColorsEnum.LILAC),
            new Cor(ColorsEnum.GRAY),
            new Cor(ColorsEnum.BROWN),
            new Cor(ColorsEnum.PURPLE)));

    public final List<Cor> getAllColors() {
        return new ArrayList<>(colorsList);
    }
}
