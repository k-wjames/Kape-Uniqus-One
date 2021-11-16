package ke.co.ideagalore.kapeunicusone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import ke.co.ideagalore.kapeunicusone.adapters.NewsAdapter;
import ke.co.ideagalore.kapeunicusone.models.Articles;
import ke.co.ideagalore.kapeunicusone.models.NewsApiResponse;

public class MainActivity extends AppCompatActivity implements SelectListener, View.OnClickListener {

    RecyclerView recyclerView;
    NewsAdapter adapter;
    ProgressDialog dialog;
    Button btn_business, btn_entertainment, btn_general, btn_health, btn_science, btn_sports, btn_technology;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Kape News");

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading News Articles...");
        dialog.show();


        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching News Articles on " + query);
                dialog.show();
                RequestManager requestManager = new RequestManager(getApplicationContext());
                requestManager.getNewsArticles(listener, "general", null);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        btn_business = findViewById(R.id.btn_business);
        btn_entertainment = findViewById(R.id.btn_entertainment);
        btn_general = findViewById(R.id.btn_general);
        btn_health = findViewById(R.id.btn_health);
        btn_science = findViewById(R.id.btn_science);
        btn_sports = findViewById(R.id.btn_sports);
        btn_technology = findViewById(R.id.btn_technology);

        btn_business.setOnClickListener(this);
        btn_entertainment.setOnClickListener(this);
        btn_general.setOnClickListener(this);
        btn_health.setOnClickListener(this);
        btn_sports.setOnClickListener(this);
        btn_technology.setOnClickListener(this);

        RequestManager requestManager = new RequestManager(this);
        requestManager.getNewsArticles(listener, "general", null);

    }

    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<Articles> list, String message) {

            if (list.isEmpty()) {

                Toast.makeText(getApplicationContext(), "Oops!No News Articles found.", Toast.LENGTH_SHORT).show();
            } else {

                showNewsArticles(list);
                dialog.dismiss();
            }

        }

        @Override
        public void onError(String message) {
            Toast.makeText(getApplicationContext(), "Oops! A communication error occurred.", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
    };

    private void showNewsArticles(List<Articles> list) {
        recyclerView = findViewById(R.id.recycler_news);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        adapter = new NewsAdapter(this, list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnArticleClicked(Articles articles) {

        startActivity(new Intent(getApplicationContext(), NewsActivity.class)
                .putExtra("data", articles));

    }

    @Override
    public void onClick(View view) {

        Button button = (Button) view;
        String category = button.getText().toString();
        dialog.setTitle("Loading News Articles on " + category);
        dialog.show();
        RequestManager requestManager = new RequestManager(this);
        requestManager.getNewsArticles(listener, category, null);

    }
}