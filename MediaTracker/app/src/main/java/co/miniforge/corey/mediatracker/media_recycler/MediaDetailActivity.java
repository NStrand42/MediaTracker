package co.miniforge.corey.mediatracker.media_recycler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import co.miniforge.corey.mediatracker.MyListActivity;
import co.miniforge.corey.mediatracker.R;
import co.miniforge.corey.mediatracker.model.MediaItem;

import static co.miniforge.corey.mediatracker.MyListActivity.mediaExtra;

public class MediaDetailActivity extends AppCompatActivity {

    EditText title;
    EditText url;
    EditText description;
    Button saveButton;
    Intent intent;

    JSONObject jsonDataFromIntent;
    private MediaItem mediaItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_item_detail);

        url = (EditText) findViewById(R.id.url);
        description = (EditText) findViewById(R.id.description);
        title = (EditText) findViewById(R.id.title);

        saveButton = (Button) findViewById(R.id.saveButton);

        url.setText(mediaItem.url);
        description.setText(mediaItem.description);
        title.setText(mediaItem.title);

        intentData();
        onClickListener();
    }


    private void intentData() {
        if (getIntent().hasExtra(MediaViewHolder.jsonInfo)) {
            String rawJson = getIntent().getStringExtra(MediaViewHolder.jsonInfo);
            try {
                this.jsonDataFromIntent = new JSONObject(rawJson);
                mediaItem = new MediaItem(this.jsonDataFromIntent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void bindData() {
        url.setText(mediaItem.url);
        description.setText(mediaItem.description);
        title.setText(mediaItem.title);

    }

    private void onClickListener() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaItem.description = description.getText().toString();
                mediaItem.url = url.getText().toString();
                mediaItem.title = title.getText().toString();

                intent = new Intent(getApplicationContext(), MyListActivity.class);

                intent.putExtra(mediaExtra, mediaItem.toJson().toString());
                startActivity(intent);

            }
        });
    }
}
