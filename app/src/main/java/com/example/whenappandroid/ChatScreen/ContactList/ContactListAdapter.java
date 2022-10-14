package com.example.whenappandroid.ChatScreen.ContactList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.whenappandroid.Data.Contact;
import com.example.whenappandroid.databinding.RecyclerviewContactBinding;

public class ContactListAdapter extends ListAdapter<Contact, ContactViewHolder> {
    private OnListItemClick onListItemClick;

    public ContactListAdapter(@NonNull DiffUtil.ItemCallback<Contact> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @NonNull RecyclerviewContactBinding binding = RecyclerviewContactBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ContactViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact current = getItem(position);
        holder.itemView.setOnClickListener((View view) -> onListItemClick.onClick(view, current));
        holder.bind(current);
    }

    static public class ContactDiff extends DiffUtil.ItemCallback<Contact> {

        @Override
        public boolean areItemsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
            return oldItem.toString().equals(newItem.toString());
        }
    }

    public interface OnListItemClick {
        void onClick(View view, Contact contact);
    }

    public void setItemClickListener(OnListItemClick callback) {
        this.onListItemClick = callback;
    }

}
