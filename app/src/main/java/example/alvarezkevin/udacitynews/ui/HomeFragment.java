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

public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<Article>> {

    private ArticlesAdapter mArticlesAdapter;
    private static final int HOME_LOADER = 0;
    private static final String LOG_TAG = HomeFragment.class.getSimpleName();
    private static final String mUrl = "https://content.guardianapis.com/search?api-key=";
    private static final String API_KEY = BuildConfig.API_KEY;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_home);
        mArticlesAdapter = new ArticlesAdapter(null, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mArticlesAdapter);

        getLoaderManager().initLoader(HOME_LOADER, null, this);
        return rootView;
    }

    @Override
    public Loader<ArrayList<Article>> onCreateLoader(int id, Bundle args) {
        return new FetchArticlesLoader(getActivity(), mUrl + API_KEY);
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
