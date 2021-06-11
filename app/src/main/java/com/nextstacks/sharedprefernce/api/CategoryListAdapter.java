package com.nextstacks.sharedprefernce.api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.nextstacks.sharedprefernce.R;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder> {

    private Context context;
    private String[] categoryTitles;

    private CategorySelectListener listener;

    private int selectedPosition = -1;

    public CategoryListAdapter(Context context) {
        this.context = context;
        this.categoryTitles = context.getResources().getStringArray(R.array.news_categories);
    }

    public void setListener(CategorySelectListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.cell_category, parent, false);
//        CategoryViewHolder holder = new CategoryViewHolder(view);
//        return holder;

        return new CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.cell_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        String catItem = categoryTitles[position];

        holder.mTvCategory.setText(catItem);

        if (position == selectedPosition) {
            holder.mllRoot.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.bg_category_selected, null));
        } else {
            holder.mllRoot.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.bg_category_unselected, null));
        }

        holder.mllRoot.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged();

            if (listener != null) {
                listener.onCategorySelected(catItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryTitles.length;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout mllRoot;
        private TextView mTvCategory;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            mllRoot = itemView.findViewById(R.id.ll_category_root);
            mTvCategory = itemView.findViewById(R.id.tv_category);
        }
    }

    public interface CategorySelectListener {
        void onCategorySelected(String category);
    }
}
