package com.phadcode.perpustakaanku;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private FragmentTransaction fragmentTransaction;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final ArrayList<ListViewSmall> listviewsmall = new ArrayList<>();
        listviewsmall.add(new ListViewSmall("Perpustakaan Bale Pustaka", "5 KM", R.drawable.bale_perpustakaan));
        listviewsmall.add(new ListViewSmall("Perpustakaan Gasibu", "6 KM", R.drawable.perpustakaan_gasibu_jawa_barat));
        listviewsmall.add(new ListViewSmall("Pitimoss Fun Library", "7 KM", R.drawable.pitimoss_fun_library));
        listviewsmall.add(new ListViewSmall("Micro Bandung Library", "5.5 KM", R.drawable.micro_library_bandung));
        listviewsmall.add(new ListViewSmall("ITB Library Building", "6 KM", R.drawable.itb_library_building));
        listviewsmall.add(new ListViewSmall("Kineruku", "8 KM", R.drawable.kineruku));
        listviewsmall.add(new ListViewSmall("Pustaka Tropis Wanadri", "5 KM", R.drawable.pustaka_tropis_wanadri));
        listviewsmall.add(new ListViewSmall("Pustakalana", "7.2 KM", R.drawable.pustakalana));

        ListViewSmallAdapter adapter = new ListViewSmallAdapter(this.getActivity(), listviewsmall);
        final ListView lvFeed = (ListView) getView().findViewById(R.id.lvFeed);

        lvFeed.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Terpencet", Toast.LENGTH_SHORT).show();
                TextView tvName = (TextView) view.findViewById(R.id.tvName);

                Bundle bundle = new Bundle();
                bundle.putString("judul", tvName.getText().toString());
                bundle.putInt("imageku", listviewsmall.get(position).getImageResourceID());
                ProfilFragment profilFragment = new ProfilFragment();
                profilFragment.setArguments(bundle);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.mainContainer, profilFragment );
                transaction.commit();
            }
        });

        lvFeed.setAdapter(adapter);
    }

}
