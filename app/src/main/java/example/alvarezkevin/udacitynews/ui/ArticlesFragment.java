package example.alvarezkevin.udacitynews.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import example.alvarezkevin.udacitynews.BuildConfig;
import example.alvarezkevin.udacitynews.R;
import example.alvarezkevin.udacitynews.adapters.ArticlesAdapter;
import example.alvarezkevin.udacitynews.data.Article;
import example.alvarezkevin.udacitynews.data.FetchArticlesLoader;

/**
 * A placeholder fragment containing a simple view.
 */
public class ArticlesFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<Article>> {

    private static final String LOG_TAG = ArticlesFragment.class.getSimpleName();
   // private static final String API_KEY = "ad393a22-cb7b-49ae-906b-c89b6b3e2adf";
    private static String mUrl;
    private static final String ARTICLE_INTENT_EXTRA = "ARTICLES_URL";
    private static final String API_KEY = BuildConfig.API_KEY;
    private static final int ARTICLE_LOADER = 2;

    private static ArticlesAdapter mArticlesAdapter;

    public ArticlesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_articles, container, false);

        mUrl = getActivity().getIntent().getStringExtra(ARTICLE_INTENT_EXTRA);

        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerview_articles);
        mArticlesAdapter = new ArticlesAdapter(null,getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mArticlesAdapter);

        getLoaderManager().initLoader(ARTICLE_LOADER,null,this);
        return rootView;
    }

    @Override
    public Loader<ArrayList<Article>> onCreateLoader(int id, Bundle args) {
        return new FetchArticlesLoader(getActivity(),mUrl + "?api-key=" + API_KEY);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Article>> loader, ArrayList<Article> data) {
        mArticlesAdapter.addList(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Article>> loader) {
        mArticlesAdapter.clear();
    }
}
