package com.example.sosbicicletta;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Contatti> mData;
    Dialog mDialog;

    public RecyclerViewAdapter(Context mContext, List<Contatti> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_contact,parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);
        //dialog
        // mettere il bottone
        mDialog = new Dialog(mContext);
        mDialog.setContentView(R.layout.dettagli);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        vHolder.item_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView dialog_name_tv = (TextView) mDialog.findViewById(R.id.dett_N);
                TextView dialog_tell_tv = (TextView) mDialog.findViewById(R.id.dett_P);
                ImageView dialog_img_tv = (ImageView) mDialog.findViewById(R.id.dett_IMG);
                Button dialog_Bcall = (Button) mDialog.findViewById(R.id.dett_chiama);
                dialog_name_tv.setText(mData.get(vHolder.getAdapterPosition()).getNome());
                dialog_tell_tv.setText(mData.get(vHolder.getAdapterPosition()).getTelefono());
                dialog_img_tv.setImageResource(mData.get(vHolder.getAdapterPosition()).getFoto());
                dialog_Bcall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       String Phone = dialog_tell_tv.getText().toString();
                       String s = "tel:" +Phone;

                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse(s));
                            mContext.startActivity(intent);


                    }

                });
               // Toast.makeText(mContext,"Dettagli"+String.valueOf(vHolder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
                mDialog.show();
                //bottone
            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_nome.setText(mData.get(position).getNome());
        holder.tv_telefono.setText(mData.get(position).getTelefono());
        holder.img.setImageResource(mData.get(position).getFoto());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout item_cont;
        private TextView tv_nome;
        private TextView tv_telefono;
        private ImageView img;
        //private Button Bcall;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_cont = (LinearLayout) itemView.findViewById(R.id.cont_item);
            tv_nome = (TextView) itemView.findViewById(R.id.name_driver);
            tv_telefono = (TextView) itemView.findViewById(R.id.cont_tell);
            img = (ImageView) itemView.findViewById(R.id.img_contact);


        }
    }

}
