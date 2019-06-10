package zystudio.cases.graphics.scrolls;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zystudio.demo.R;

public class CaseNestedScrollFragment extends Fragment {


    public static void work(FragmentActivity activity ) {
        CaseNestedScrollFragment frag=new CaseNestedScrollFragment();
        activity.getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,frag).commit();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.case_nestedscroll_frag, container, false);
    }
}
