package com.lawlett.quizapp.presentation.history.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.lawlett.quizapp.R;
import com.lawlett.quizapp.data.model.History;
import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private final HistoryListener listener;
    List<History> list = new ArrayList<>();

    public HistoryAdapter(HistoryListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.history_holder, parent, false);
        HistoryViewHolder viewHolder = new HistoryViewHolder(view, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void update(List<History> history) {
        this.list = history;
        notifyDataSetChanged();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_category, tv_correctAnswers, tv_difficulty, tv_date;
        private ImageView dots;
        private HistoryListener listener;

        public HistoryViewHolder(@NonNull View itemView, HistoryListener listener) {
            super(itemView);
            this.listener = listener;

            initView();
            dots.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDotsClick(getAdapterPosition(), v);
                }
            });

        }
        private void initView() {
            dots = itemView.findViewById(R.id.dots);
            tv_category = itemView.findViewById(R.id.history_category_value);
            tv_difficulty = itemView.findViewById(R.id.history_difficulty_value);
            tv_correctAnswers = itemView.findViewById(R.id.history_answers_value);
            tv_date = itemView.findViewById(R.id.date);
        }

        public void bind(History history) {
            tv_category.setText(history.getCategory());
            tv_difficulty.setText(history.getDifficulty());
            tv_correctAnswers.setText(history.getCorrectAnswers() + "/" + history.getQuestionAmount());
            tv_date.setText(history.getCreateAt().toLocaleString());
        }
    }


    public interface HistoryListener {
        void onDotsClick(int position, View v);
    }
    }
