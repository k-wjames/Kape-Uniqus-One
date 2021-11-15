package ke.co.ideagalore.kapeunicusone;

import java.util.List;

import ke.co.ideagalore.kapeunicusone.models.Articles;

public interface OnFetchDataListener<NewsApiResponse> {

    void onFetchData(List<Articles> list, String message);
    void onError(String message);
}
