package com.phadcode.perpustakaanku;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {


    public ProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView tvPerpusName = (TextView) getView().findViewById(R.id.tvPerpusName);
        ImageView gbr_profile = (ImageView) getView().findViewById(R.id.ivProfil);
        Button btndetail = (Button)getView().findViewById(R.id.detail);
        btndetail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(getActivity(), ProfilFragmentDetailPerpus.class);
                    startActivity(intent);

            }
        });
        Bundle bundle = getArguments();
        tvPerpusName.setText(bundle.getString("judul"));
        gbr_profile.setImageResource(bundle.getInt("imageku"));

        ((MainActivity) getActivity()).setActionBarTitle(((TextView) getView().findViewById(R.id.tvPerpusName)).getText().toString());

        final ArrayList<ListViewSmall> listviewsmall = new ArrayList<>();
        listviewsmall.add(new ListViewSmall("10 JURUS TERLARANG", "Tersedia", R.drawable.jurus_terlarang));
        listviewsmall.add(new ListViewSmall("100 FAKTA MENCERDASKAN ANDA", "Tidak Tersedia", R.drawable.fakta_mencerdaskan_anda));
        listviewsmall.add(new ListViewSmall("100 FOREX MENGHASILKAN UANG", "Tersedia", R.drawable.forex_menghasilkan_uang));
        listviewsmall.add(new ListViewSmall("100 PUDING FAVORITE", "Tersedia", R.drawable.puding_favorite));
        listviewsmall.add(new ListViewSmall("1001 TIPS DAPUR", "Tidak Tersedia", R.drawable.tip_dapur));
        listviewsmall.add(new ListViewSmall("1033 ALASAN UNTUK TERSENYUM", "Tersedia", R.drawable.alasan_untuk_tersenyum));
        listviewsmall.add(new ListViewSmall("200 REKOR MENAKLUKAN BUMI NUSANTARA", "Tidak Tersedia", R.drawable.rekor_200_rekor_menakjubkan_bumi_nusantara));
        listviewsmall.add(new ListViewSmall("ASUHAN KEBIDANAN PERSALINAN & KELAHIRAN", "Tersedia", R.drawable.kebidanan));
        listviewsmall.add(new ListViewSmall("BIOLOGI REPRODUKSI KEHAMILAN DAN PERSALINAN", "Tersedia", R.drawable.biologi_kehamilan_persalinan));
        listviewsmall.add(new ListViewSmall("BUKU PINTAR MIND MAP : ANAK MUDAH MENGHAPAL", "Tersedia", R.drawable.mind_map_anak));
        listviewsmall.add(new ListViewSmall("BUKU SAKU PERCAKAPAN BAHASA INGGRIS SEHARI HARI", "Tersedia", R.drawable.percakapan_binggris));
        listviewsmall.add(new ListViewSmall("BUSANA BATIK KERJA", "Tersedia", R.drawable.batik_kerja));
        listviewsmall.add(new ListViewSmall("CAMILAN KHAS INDONESIA", "Tersedia", R.drawable.camila_khas_indonesia));
        listviewsmall.add(new ListViewSmall("CARA MEMBACA WAJAH : MENJADI SESEORANG YANG PALING DIINGINKAN DAN MENYENANGKAN", "Tersedia", R.drawable.cara_cepat_menjadi_seseorang_menyenangkan));
        listviewsmall.add(new ListViewSmall("CARA JITU MENGATASI KENCING MANIS", "Tersedia", R.drawable.kencing_manis));
        listviewsmall.add(new ListViewSmall("CHRISTMAS EVE AT FRIDAY HARBOR – MALAM PENUH KEAJAIBAN", "Tersedia", R.drawable.lisa_kleypas));
        listviewsmall.add(new ListViewSmall("DAHSYATNYA KHASIAT SIRSAK UNTUK BANYAK PENYAKIT…", "Tersedia", R.drawable.sirsak));
        listviewsmall.add(new ListViewSmall("DASYATNYA OTAK TENGAH", "Tersedia", R.drawable.dahsyat_otak_tengah));
        listviewsmall.add(new ListViewSmall("DAUN YANG JATUH TAK PERNAH MEMBENCI ANGIN", "Tersedia", R.drawable.daun_yang_jatuh));
        listviewsmall.add(new ListViewSmall("DESENTRALISASI FISKAL POLITIK DAN PERUBAHAN", "Tersedia", R.drawable.desentrasi_fisika));
        listviewsmall.add(new ListViewSmall("DOKUMENTASI KEBIDANAN", "Tersedia", R.drawable.dokumentasi_kebidanan));
        listviewsmall.add(new ListViewSmall("ETIKA & KODE ETIK PROFESI KEBIDANAN", "Tersedia", R.drawable.etika_kode));
        listviewsmall.add(new ListViewSmall("FISIKA KEDOKTERAN", "Tersedia", R.drawable.fisika_kedokteran));
        listviewsmall.add(new ListViewSmall("HABIBIE & AINUN", "Tersedia", R.drawable.habibi_ainun));
        listviewsmall.add(new ListViewSmall("HIROMI KULANGKAHKAN KAKIKU MENGIKUTI LANGKAH KAKI IBU", "Tersedia", R.drawable.hiromi));
        listviewsmall.add(new ListViewSmall("HISTORICAL ROMANCE : PERNIKAHAN MENJELANG PAGI", "Tersedia", R.drawable.historical_romance));
        listviewsmall.add(new ListViewSmall("INSPIRASI RUMAH MINIMALIS 100-150 JUTA", "Tersedia", R.drawable.rumah_inspirasi));
        listviewsmall.add(new ListViewSmall("JAGO 3 BAHASA UNTUK ANAK INDONESIA-ARAB-INGGRIS", "Tersedia", R.drawable.jago_bahasa));
        listviewsmall.add(new ListViewSmall("KEKUASAAN DAN PRILAKU KORUPSI", "Tersedia", R.drawable.korupsi));
        listviewsmall.add(new ListViewSmall("KELEMAHANKU ADALAH KEKUATANKU", "Tersedia", R.drawable.kelemahan));

        ListViewSmallAdapter adapter = new ListViewSmallAdapter(this.getActivity(), listviewsmall);
        ListView lvBook = (ListView) getView().findViewById(R.id.lvBook);

        lvBook.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tvName = (TextView) view.findViewById(R.id.tvName);
                Intent intent = new Intent(getActivity(), Pop.class);
                intent.putExtra("judulBuku", tvName.getText().toString());
                intent.putExtra("gambarku",listviewsmall.get(position).getImageResourceID());
                startActivity(intent);
            }
        });

        lvBook.setAdapter(adapter);


    }

}
