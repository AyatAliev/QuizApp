package com.lawlett.quizapp.presentation.history;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.lawlett.quizapp.App;
import com.lawlett.quizapp.R;
import com.lawlett.quizapp.core.CoreFragment;
import com.lawlett.quizapp.data.model.History;
import com.lawlett.quizapp.presentation.history.recycler.HistoryAdapter;

import java.util.List;

import static androidx.lifecycle.ViewModelProviders.*;
import static com.google.gson.reflect.TypeToken.get;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends CoreFragment implements HistoryAdapter.HistoryListener {
    private HistoryViewModel mViewModel;
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private List<History> currentHistories;


    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_history;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_builder(view);
    }

    private void rv_builder(View view) {
        recyclerView = view.findViewById(R.id.history_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                getActivity(),
                RecyclerView.VERTICAL,
                false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HistoryAdapter(this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this)
                .get(HistoryViewModel.class);

//
        mViewModel.historyLiveData.observe(getActivity(), new Observer<List<History>>() {
            @Override
            public void onChanged(List<History> histories) {
                if (histories != null)
                    adapter.update(histories);
                currentHistories = histories;

            }
        });

    }

    private void showPopUp(View v, int position) {
        PopupMenu popupMenu = new PopupMenu(getContext(), v);
        popupMenu.inflate(R.menu.popup_menu);

        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.delete:
                    App.quizDatabase.historyDao().deleteById(currentHistories.get(position).getId());
                    return true;
                case R.id.share:
                    History currentHistory = currentHistories.get(position);
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("plain/text");
                    intent.putExtra(Intent.EXTRA_TEXT, "game id: " + currentHistory.getId()
                            + "\ncategory: " + currentHistory.getCategory()
                            + "\ncorrect answers: " + currentHistory.getCorrectAnswers() + "/" + currentHistory.getQuestionAmount()
                            + "\ndifficulty: " + currentHistory.getDifficulty()
                            + "\ndate: " + currentHistory.getCreateAt());
                    startActivity(Intent.createChooser(intent, ""));
                    return true;
            }
            return false;
        });

        popupMenu.show();
    }

    @Override
    public void onDotsClick(int position, View v) {
        showPopUp(v,position);
    }
}
