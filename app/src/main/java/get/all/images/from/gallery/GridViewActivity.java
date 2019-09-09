package get.all.images.from.gallery;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import android.net.Uri;

public class GridViewActivity extends AppCompatActivity {

    private Context context = GridViewActivity.this;
    private GridView gridview;
    private ArrayList<ImagePathModel> imagePathModelArrayList;
    private CustomBaseAdapter customBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        initView();
        initObject();
        generateItemsList();
    }

    private void initView() {
        gridview = findViewById(R.id.grid_view);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast toast = Toast.makeText(getApplicationContext(), ""+imagePathModelArrayList.get(position).getAbsoluteImagePath(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void initObject() {
        imagePathModelArrayList = new ArrayList<ImagePathModel>();
    }

    private void generateItemsList()
    {
        customBaseAdapter = new CustomBaseAdapter(context, getAllShownImagesPath());
        gridview.setAdapter(customBaseAdapter);
    }

    private ArrayList<ImagePathModel> getAllShownImagesPath() {

        int int_position = 0;
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;

        String absolutePathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projections =
                {MediaStore.Images.Media._ID,
                        MediaStore.Images.Media.DATA,
                        MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                        MediaStore.Images.Media.BUCKET_ID,
                        MediaStore.Images.Media.DISPLAY_NAME};

        cursor = getApplicationContext().getContentResolver().query(uri, projections, null, null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);

            imagePathModelArrayList.add(new ImagePathModel(absolutePathOfImage));
        }
        Collections.reverse(imagePathModelArrayList);
        return imagePathModelArrayList;
    }
}
