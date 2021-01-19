package com.example.ceep.classes.database.conveter;

import androidx.room.TypeConverter;

import com.example.ceep.classes.constantes.general.coresEnum;

public class coresEnumConverter {

    @TypeConverter
   public String coresEnumToString(coresEnum coresEnume){
       if(coresEnume!=null){
           return coresEnume.name();
       }
       return null;
   }
   @TypeConverter
   public coresEnum stringToCorEnum(String valor){
        if(valor!=null){
            return coresEnum.valueOf(valor);
        }
        return null;
   }
}
