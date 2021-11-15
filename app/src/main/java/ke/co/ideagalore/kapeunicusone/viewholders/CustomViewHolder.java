package ke.co.ideagalore.kapeunicusone.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import ke.co.ideagalore.kapeunicusone.R;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    public TextView text_title, text_source;
    public ImageView image_headline;
    public CardView cardView;
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        text_title=itemView.findViewById(R.id.text_title);
        text_source=itemView.findViewById(R.id.text_source);
        image_headline=itemView.findViewById(R.id.news_image);
        cardView=itemView.findViewById(R.id.news_container);
    }
}
