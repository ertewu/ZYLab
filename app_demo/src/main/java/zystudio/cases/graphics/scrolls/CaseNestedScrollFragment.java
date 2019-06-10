package zystudio.cases.graphics.scrolls;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zystudio.demo.R;

public class CaseNestedScrollFragment extends Fragment {


    private RecyclerView mRecycleView;
    private RecyclerviewAdapter mAdapter;


    public static void work(FragmentActivity activity) {
        CaseNestedScrollFragment frag = new CaseNestedScrollFragment();
        activity.getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, frag).commit();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.case_nestedscroll_frag, container, false);
        mRecycleView = content.findViewById(R.id.rc);
        RecyclerView.LayoutManager llm = initLayoutManager();
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String str = "第" + i + "个Item";
            data.add(str);
        }
        mAdapter = new RecyclerviewAdapter(getContext(), data);
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setLayoutManager(llm);
        return content;
    }

    protected RecyclerView.LayoutManager initLayoutManager() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setSmoothScrollbarEnabled(true);
        return llm;
    }

    public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {

        private Context context;
        private List<String> data;

        public RecyclerviewAdapter(Context context, List<String> data) {
            this.context = context;
            this.data = data;

        }

        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            holder.name.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView name;

            public ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.text_item_view);
            }
        }

    }

}
