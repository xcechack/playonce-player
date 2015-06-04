package cz.muni.fi.pv239.playonceplayer;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class StreamRadioActivity extends ActionBarActivity {

    private Handler handler;
    private ArrayList<Radio> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream_radio);

        data = new ArrayList<Radio>();


        data.add( new Radio(1, "Radio Slovensko","http://icecast.stv.livebox.sk/slovensko_128.mp3"));

        ListView lv = (ListView) findViewById(R.id.radio_list);
        lv.setAdapter(new ArrayAdapter<Radio>(this, R.layout.radio_list, R.id.radioname, data));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView< ?> parent, View view, int position, long id) {
                //data.get(position).getUri();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //menu item selected
        switch (item.getItemId()) {
            case R.id.action_shuffle:
//                musicSrv.setShuffle();
                break;
            case R.id.action_playlist:
                this.showPlaylist();

                break;
            case R.id.action_stream:
                break;
            case R.id.action_generated_playlists:
                break;

        }
        return super.onOptionsItemSelected(item);

    }


    //show playlist activity
    private void showPlaylist(){

        handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(StreamRadioActivity.this, PlaylistActivity.class);
                        // i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        StreamRadioActivity.this.startActivity(i);
                    }
                });

            }
        };

        new Thread(runnable).start();
    }
}

//TODO opravit zobrazovanie playera, opravit tlacitka, pridat playlisty, pridat shuffle, nazov radia