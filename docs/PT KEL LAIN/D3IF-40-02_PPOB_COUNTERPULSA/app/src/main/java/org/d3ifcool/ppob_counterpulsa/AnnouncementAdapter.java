package org.d3ifcool.ppob_counterpulsa;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.d3ifcool.ppob_counterpulsa.controller.StorageController;
import org.d3ifcool.ppob_counterpulsa.model.AnnouncemenetModel;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AnnouncementAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<AnnouncemenetModel> announcemenetModels;

    AnnouncementAdapter(Activity activity) {
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        StorageController storageController = new StorageController(activity);
        announcemenetModels = new ArrayList<>();
        Cursor getAnnouncement = storageController.getAnnouncement();
        while (getAnnouncement.moveToNext()){
            announcemenetModels.add(new AnnouncemenetModel(
                    getAnnouncement.getInt(getAnnouncement.getColumnIndex("id_announcement")),
                    getAnnouncement.getString(getAnnouncement.getColumnIndex("title")),
                    getAnnouncement.getString(getAnnouncement.getColumnIndex("description")),
                    getAnnouncement.getString(getAnnouncement.getColumnIndex("date")),
                    getAnnouncement.getString(getAnnouncement.getColumnIndex("time")),
                    getAnnouncement.getString(getAnnouncement.getColumnIndex("image_resource"))
            ));
        }
    }

    @Override
    public int getCount() {
        return announcemenetModels.size();
    }

    @Override
    public Object getItem(int i) {
        return announcemenetModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint({"ViewHolder", "InflateParams", "SetTextI18n"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = this.layoutInflater.inflate(R.layout.item_announcement, null);
        TextView announcement_title, announcement_date, announcement_description;
        ImageView announcement_thumbnail;

        announcement_title = view.findViewById(R.id.announcement_title);
        announcement_date = view.findViewById(R.id.announcement_date);
        announcement_description = view.findViewById(R.id.announcement_description);
        announcement_thumbnail = view.findViewById(R.id.announcement_thumbnail);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy/MM/dd", new Locale("in", "ID"));
        try {
            Date date = newDateFormat.parse(announcemenetModels.get(i).getDate());
            newDateFormat.applyPattern("EEEE d MMM yyyy");
            String myDate = newDateFormat.format(date);
            String myTime = announcemenetModels.get(i).getTime();

            announcement_title.setText(this.announcemenetModels.get(i).getTitle());
            announcement_date.setText(myDate + " | " + myTime);
            if (this.announcemenetModels.get(i).getDescription().length() < 80)
                announcement_description.setText(this.announcemenetModels.get(i).getDescription());
            else
                announcement_description.setText(this.announcemenetModels.get(i).getDescription().substring(0, 80));
            new loadImage(announcement_thumbnail, "http://counterpulsa.xyz/try/assets/pengumuman/" + this.announcemenetModels.get(i).getImage_resource()).execute();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return view;
    }

    @SuppressLint("StaticFieldLeak")
    class loadImage extends AsyncTask<Void, Void, Bitmap>{
        ImageView imageView;
        String resource;
        loadImage(ImageView imageView, String resource) {
            this.imageView = imageView;
            this.resource = resource;
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            URL url = null;
            try {
                url = new URL(this.resource);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Bitmap bitmap = null;
            try {
                assert url != null;
                bitmap = BitmapFactory.decodeStream(url.openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap aVoid) {
            super.onPostExecute(aVoid);
            imageView.setImageBitmap(aVoid);
        }
    }
}
