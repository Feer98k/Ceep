package com.example.ceep.classes.ui.adapter.listaCoresAdapter;

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
import com.example.ceep.classes.constants.general.ColorsEnum;
import com.example.ceep.classes.model.Cor;

import java.util.List;

public class ColorsAdapterForm extends RecyclerView.Adapter<ColorsAdapterForm.ColorsHolder> {

    private final Context context;
    private final List<Cor> list;
    private OnClickLCoresListerner onClickListernet;




    public ColorsAdapterForm(Context context, List<Cor> list) {
        this.context = context;
        this.list = list;
    }


    public void setOnClickListerner(OnClickLCoresListerner onClickListernet) {
        this.onClickListernet = onClickListernet;
    }

    @NonNull
    @Override
    public ColorsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflater = LayoutInflater
                .from(context)
                .inflate(R.layout.item_cores, parent, false);

        return new ColorsHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorsHolder holder, int position) {
        Cor color = list.get(position);
        ColorsEnum colorPosition = color.getCor();
        setColorByHolder(holder, colorPosition);
        holder.vincule(color);
    }

    private void setColorByHolder(ColorsHolder holder, ColorsEnum colorDefault) {
        switch (colorDefault) {
            case WHITE:
                setDrawableColor(holder.item.getContext(), holder.item.getBackground(), R.color.BRANCO);
                break;
            case BLUE:
                setDrawableColor(holder.item.getContext(), holder.item.getBackground(), R.color.AZUL);
                break;
            case RED:
                setDrawableColor(holder.item.getContext(), holder.item.getBackground(), R.color.VERMELHO);
                break;
            case YELLOW:
                setDrawableColor(holder.item.getContext(), holder.item.getBackground(), R.color.AMARELO);
                break;

            case GREEN:
                setDrawableColor(holder.item.getContext(), holder.item.getBackground(), R.color.VERDE);
                break;

            case LILAC:
                setDrawableColor(holder.item.getContext(), holder.item.getBackground(), R.color.LILÁS);
                break;
            case GRAY:
                setDrawableColor(holder.item.getContext(), holder.item.getBackground(), R.color.CINZA);
                break;
            case BROWN:
                setDrawableColor(holder.item.getContext(), holder.item.getBackground(), R.color.MARROM);
                break;
            case PURPLE:
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
    class ColorsHolder extends RecyclerView.ViewHolder {
        Cor colors;
        final View item;

        public ColorsHolder(View itemView) {
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
