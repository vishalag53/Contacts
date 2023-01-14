package com.vishalag53.contacts;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.ArrayList;

public class RecyclerContactAdapeter extends Adapter<RecyclerContactAdapeter.ViewHolder> {

    Context context;
    ArrayList<ContactModel> arrContacts;

    RecyclerContactAdapeter(Context context, ArrayList<ContactModel> arrContacts){
        this.context = context;
        this.arrContacts = arrContacts;
    }

    @NonNull
    @Override
    public RecyclerContactAdapeter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerContactAdapeter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ContactModel model = (ContactModel)arrContacts.get(position);
        holder.imgContact.setImageResource(arrContacts.get(position).img);
        holder.txtName.setText(arrContacts.get(position).name);
        holder.txtNumber.setText(arrContacts.get(position).number);

        holder.llRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_contact);

                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtNumber = dialog.findViewById(R.id.edtNumber);
                Button btnAction  = dialog.findViewById(R.id.btnAction);
                TextView txtTitle = dialog.findViewById(R.id.txtTitle);
                txtTitle.setText("Update Contact");
                btnAction.setText("Update");

                edtName.setText(arrContacts.get(position).name);
                edtNumber.setText(arrContacts.get(position).number);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name="",number="";
                        if(!edtName.getText().toString().equals("")){
                            name = edtName.getText().toString();
                        }
                        else{
                            Toast.makeText(context,"Please Enter the Contact Name",Toast.LENGTH_SHORT).show();
                        }

                        if(!edtNumber.getText().toString().equals("")){
                            number = edtNumber.getText().toString();
                        }
                        else{
                            Toast.makeText(context,"Please Enter the Contact Number",Toast.LENGTH_SHORT).show();
                        }

                        arrContacts.set(position,new ContactModel(arrContacts.get(position).img,name,number));
                        notifyItemChanged(position);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        holder.llRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete Contact")
                        .setMessage("Are you sure want to delete?")
                        .setIcon(R.drawable.ic_baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                arrContacts.remove(position);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                builder.show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llRow;
        TextView txtName,txtNumber;
        ImageView imgContact;

        public ViewHolder(View itemView){
            super(itemView);
            llRow = itemView.findViewById(R.id.llRow);
            imgContact = itemView.findViewById(R.id.imgContact);
            txtName = itemView.findViewById(R.id.txtName);
            txtNumber = itemView.findViewById(R.id.txtNumber);
        }
    }
}
