package masterous.if4b.ensiklopedia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import masterous.if4b.ensiklopedia.databinding.ItemEnsiklopediaBinding;
import masterous.if4b.ensiklopedia.db.Ensiklopedia;

public class EnsiklopediaViewAdapter extends RecyclerView.Adapter<EnsiklopediaViewAdapter.ViewHolder> {
    private List<Ensiklopedia> data = new ArrayList<>();
    private OnClickListener listener;

    public void setData(List<Ensiklopedia> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public EnsiklopediaViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemEnsiklopediaBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EnsiklopediaViewAdapter.ViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();
        Ensiklopedia ensiklopedia = data.get(pos);
        holder.itemEnsiklopediaBinding.tvTitle.setText(ensiklopedia.getTitle());
        holder.itemEnsiklopediaBinding.tvDescription.setText(ensiklopedia.getDescription());
        holder.itemEnsiklopediaBinding.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onEditClicked(ensiklopedia);
                }
            }
        });
        holder.itemEnsiklopediaBinding.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onDeleteClicked(ensiklopedia.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemEnsiklopediaBinding itemEnsiklopediaBinding;

        public ViewHolder(@NonNull ItemEnsiklopediaBinding itemView) {
            super(itemView.getRoot());
            itemEnsiklopediaBinding = itemView;
        }
    }

    public interface OnClickListener {
        void onEditClicked(Ensiklopedia ensiklopedia);
        void onDeleteClicked(int id);
    }
}