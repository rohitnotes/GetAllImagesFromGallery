package get.all.images.from.gallery;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.io.File;
import java.util.ArrayList;

public class CustomBaseAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ImagePathModel> itemModelList;
    private LayoutInflater layoutInflater;

    public CustomBaseAdapter(Context context, ArrayList<ImagePathModel> itemModels) {
        this.context = context;
        this.itemModelList = itemModels;
        layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return (null != itemModelList ? itemModelList.size() : 0);
    }

    @Override
    public Object getItem(int position) {
        return (null != itemModelList ? itemModelList.get(position) : null);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewHolder itemViewHolder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_item, parent, false);
              /*
            OR
            convertView = layoutInflater.inflate(R.layout.layout_list_item,null);
             */
            itemViewHolder = new ItemViewHolder(convertView);
            convertView.setTag(itemViewHolder);
        } else {
            itemViewHolder = (ItemViewHolder) convertView.getTag();
        }

        ImagePathModel rowItem = (ImagePathModel) getItem(position);
        ((ItemViewHolder) itemViewHolder).setData(rowItem);
        return convertView;
    }

    private class ItemViewHolder
    {
        ImageView itemImageView;

        public ItemViewHolder(View item)
        {
            itemImageView = item.findViewById(R.id.image_view);
        }

        public void setData(ImagePathModel itemPosition)
        {
            itemImageView.setImageURI(Uri.fromFile(new File(itemPosition.getAbsoluteImagePath())));
        }
    }
}
