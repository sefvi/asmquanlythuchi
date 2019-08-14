package vn.sefviapp.asm.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.sefviapp.asm.Database.Database;
import vn.sefviapp.asm.Fragments.LoaiThuFragment;
import vn.sefviapp.asm.Modle.LoaiThu;
import vn.sefviapp.asm.R;

public class LoaiThuAdapter extends RecyclerView.Adapter<LoaiThuAdapter.MyViewHolder> {

    private List<LoaiThu> loaiThus;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    public LoaiThuAdapter(Context context,List<LoaiThu> loaiThus) {
        this.loaiThus = loaiThus;
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtNameLoaiThu;
        ImageView imgEdtLoaiThu, imgDeleteLoaiThu;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtNameLoaiThu = itemView.findViewById(R.id.txtCustomNameLoaiThu);
            imgEdtLoaiThu = itemView.findViewById(R.id.imgCustomEditLoaiThu);
            imgDeleteLoaiThu = itemView.findViewById(R.id.imgCustomDeleteLoaiThu);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = mLayoutInflater.inflate(R.layout.custom_loai_thu,parent,false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(LoaiThuAdapter.MyViewHolder holder, final int position) {
        final LoaiThu loaiThu = loaiThus.get(position);
        holder.txtNameLoaiThu.setText(loaiThu.getTenLoaiThu());
        holder.imgDeleteLoaiThu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                android.support.v7.app.AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Bạn có muốn xóa Loại Thu "+ loaiThu.getTenLoaiThu() +" này không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoaiThuFragment.loaiThuDelete(loaiThu.getId());
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        holder.imgEdtLoaiThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiThuFragment.loaiThuEdit(mContext, loaiThu.getId(), loaiThu.getTenLoaiThu());
            }
        });
    }

    @Override
    public int getItemCount() {
        return loaiThus.size();
    }
}