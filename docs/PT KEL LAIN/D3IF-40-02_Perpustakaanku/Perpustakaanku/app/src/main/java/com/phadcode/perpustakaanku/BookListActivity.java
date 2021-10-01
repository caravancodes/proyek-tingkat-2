package com.phadcode.perpustakaanku;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.phadcode.perpustakaanku.helper.Session;
import com.phadcode.perpustakaanku.listview.ListViewSmall;
import com.phadcode.perpustakaanku.listview.ListViewSmallAdapter;
import com.phadcode.perpustakaanku.listview.ListViewSmallLoader;

import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<ListViewSmall>>{
    private LoaderManager loaderManager;
    private ListViewSmallAdapter adapter;
    private Session session;

    private ListView lvBook;
    private TextView tvEmptyView;
    private ProgressBar pbLoading;

    private static final int LISTVIEWSMALL_LOADER_ID = 1;

    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        getSupportActionBar().setTitle("Daftar Buku");

        session = new Session(this);

        loaderManager = getLoaderManager();
        loaderManager.initLoader(LISTVIEWSMALL_LOADER_ID, null, this);

        lvBook = (ListView) findViewById(R.id.lvBook);
        adapter = new ListViewSmallAdapter(this, new ArrayList<ListViewSmall>());
        lvBook.setAdapter(adapter);
        lvBook.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tvId = (TextView)view.findViewById(R.id.tvID);
                Intent intent = new Intent(BookListActivity.this, PopBookActivity.class);
                session.setSessionString("id_buku", tvId.getText().toString());
                startActivity(intent);
            }
        });

        pbLoading = (ProgressBar) findViewById(R.id.pbLoading);
        tvEmptyView = (TextView) findViewById(R.id.tvEmptyView);
        lvBook.setEmptyView(tvEmptyView);
    }

    @Override
    public Loader<List<ListViewSmall>> onCreateLoader(int i, Bundle bundle) {
        return new ListViewSmallLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<ListViewSmall>> loader, List<ListViewSmall> listViewSmalls) {
        pbLoading.setVisibility(View.GONE);

        tvEmptyView.setText("Data buku kosong.");

        adapter.clear();

        if (listViewSmalls != null && !listViewSmalls.isEmpty()){
            adapter.addAll(listViewSmalls);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<ListViewSmall>> loader) {
        adapter.clear();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getLoaderManager().restartLoader(0, null, this);
    }
}
