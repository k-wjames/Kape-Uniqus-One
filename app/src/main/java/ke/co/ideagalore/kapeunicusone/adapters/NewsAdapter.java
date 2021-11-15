package ke.co.ideagalore.kapeunicusone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import ke.co.ideagalore.kapeunicusone.R;
import ke.co.ideagalore.kapeunicusone.SelectListener;
import ke.co.ideagalore.kapeunicusone.models.Articles;
import ke.co.ideagalore.kapeunicusone.viewholders.CustomViewHolder;

public class NewsAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private Context context;
    private List<Articles> articlesList;
    private SelectListener listener;

    public NewsAdapter(Context context, List<Articles> articlesList, SelectListener listener) {
        this.context = context;
        this.articlesList = articlesList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.text_title.setText(articlesList.get(position).getTitle());
        holder.text_source.setText(articlesList.get(position).getSource().getName());

        if (articlesList.get(position).getUrlToImage()!=null) {

            Glide.with(context).load(articlesList.get(position).getUrlToImage()).into(holder.image_headline);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnArticleClicked(articlesList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }
}
