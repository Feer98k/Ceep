package com.example.ceep.classes.adapter.listaCoresAdapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ceep.R;
import com.example.ceep.classes.constantes.general.coresEnum;
import com.example.ceep.classes.model.Cor;

import java.util.List;

public class FormularioCoresAdapter extends RecyclerView.Adapter<FormularioCoresAdapter.coresHolder> {

    private final Context context;
    private final List<Cor> list;
    private onClickLCoresListerner onClickListernet;
    private int holder = 0;



    public FormularioCoresAdapter(Context context, List<Cor> list) {
        this.context = context;
        this.list = list;
    }


    public void setOnClickListerner(onClickLCoresListerner onClickListernet) {
        this.onClickListernet = onClickListernet;
    }

    @NonNull
    @Override
    public coresHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        holder++;
        View inflater = LayoutInflater
                .from(context)
                .inflate(R.layout.item_cores, parent, false);

        return new coresHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull coresHolder holder, int position) {
        Cor cor = list.get(position);
        coresEnum colorPosition = cor.getCor();
        setColorByHolder(holder, colorPosition);
        holder.vincule(cor);
    }

    private void setColorByHolder(coresHolder holder, coresEnum colorDefault) {
        switch (colorDefault) {
            case BRANCO:
                setDrawableColor(holder.item.getContext(), holder.item.getBackground(), R.color.BRANCO);
                break;
            case AZUL:
                setDrawableColor(holder.item.getContext(), holder.item.getBackground(), R.color.AZUL);
                break;
            case VERMELHO:
                setDrawableColor(holder.item.getContext(), holder.item.getBackground(), R.color.VERMELHO);
                break;
            case AMARELO:
                setDrawableColor(holder.item.getContext(), holder.item.getBackground(), R.color.AMARELO);
                break;

            case VERDE:
                setDrawableColor(holder.item.getContext(), holder.item.getBackground(), R.color.VERDE);
                break;

            case LILAS:
                setDrawableColor(holder.item.getContext(), holder.item.getBackground(), R.color.LILÁS);
                break;
            case CINZA:
                setDrawableColor(holder.item.getContext(), holder.item.getBackground(), R.color.CINZA);
                break;
            case MARROM:
                setDrawableColor(holder.item.getContext(), holder.item.getBackground(), R.color.MARROM);
                break;
            case ROXO:
                setDrawableColor(holder.item.getContext(), holder.item.getBackground(), R.color.ROXO);
                break;
        }
    }

    public static void setDrawableColor(Context context, Drawable drawable, int color) {
        Drawable drawableWrap = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(context, color));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class coresHolder extends RecyclerView.ViewHolder {
        Cor colors;
        final View item;

        public coresHolder(View itemView) {
            super(itemView);
            itemClick(itemView);
            item = itemView.findViewById(R.id.item_cores);

        }

        private void itemClick(View itemView) {
            itemView.setOnClickListener(v -> onClickListernet.onItemClick(colors));
        }

        public void vincule(Cor colors) {
            this.colors = colors;

        }
    }

}
