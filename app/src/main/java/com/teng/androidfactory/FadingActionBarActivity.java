package com.teng.androidfactory;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by teng on 10/18/16.
 */
public class FadingActionBarActivity extends FragmentActivity{
        private ArrayAdapter<String> adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            FadingActionBarHelper helper = new FadingActionBarHelper()
                    .actionBarBackground(R.drawable.ab_background)
                    .headerLayout(R.layout.header)
                    .contentLayout(R.layout.activity_listview);
            setContentView(helper.createView(this));
            helper.initActionBar(this);

            ListView listView = (ListView) findViewById(android.R.id.list);
            ArrayList<String> items = loadItems(R.raw.nyc_sites);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
            listView.setAdapter(adapter);
        }

        /**
         * @return A list of Strings read from the specified resource
         */
        private ArrayList<String> loadItems(int rawResourceId) {
            try {
                ArrayList<String> countries = new ArrayList<String>();
                InputStream inputStream = getResources().openRawResource(rawResourceId);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    countries.add(line);
                }
                reader.close();
                return countries;
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.listview_activity_menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == R.id.action_change_dataset) {
                changeDataset();
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        private void changeDataset() {
            adapter.notifyDataSetChanged();
        }
}
