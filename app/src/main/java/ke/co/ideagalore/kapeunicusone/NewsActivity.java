package ke.co.ideagalore.kapeunicusone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ke.co.ideagalore.kapeunicusone.models.Articles;

public class NewsActivity extends AppCompatActivity {

    Articles articles;

    TextView text_title, text_author,text_time, text_detail, text_content;
    ImageView img_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        text_title=findViewById(R.id.text_title);
        text_author=findViewById(R.id.text_author);
        text_time=findViewById(R.id.text_time);
        text_detail=findViewById(R.id.text_detail);
        text_content=findViewById(R.id.text_content);

        img_news=findViewById(R.id.img_news);

        articles= (Articles) getIntent().getSerializableExtra("data");

        getSupportActionBar().setTitle("Article by "+articles.getAuthor());

        text_title.setText(articles.getTitle());
        text_author.setText(articles.getAuthor());
        text_time.setText(articles.getPublishedAt());
        text_detail.setText(articles.getDescription());
        text_content.setText(articles.getContent());

        Glide.with(getApplicationContext()).load(articles.getUrlToImage()).into(img_news);

    }
}