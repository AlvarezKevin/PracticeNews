package example.alvarezkevin.udacitynews.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import example.alvarezkevin.udacitynews.R;
import example.alvarezkevin.udacitynews.data.Categories;
import example.alvarezkevin.udacitynews.data.FetchCategoriesLoader;

public class CategoriesFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<Categories>> {

    private ArrayAdapter<String> mAdapter;
    private ListView mCategoriesListView;
    private static final String LOG_TAG = CategoriesFragment.class.getSimpleName();
    private static final int CATEGORY_LOADER = 1;
    private static final String ARTICLE_INTENT_EXTRA = "ARTICLES_URL";

    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);
        mCategoriesListView = (ListView) rootView.findViewById(R.id.listview_categories);
        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
        mCategoriesListView.setAdapter(mAdapter);


        getLoaderManager().initLoader(CATEGORY_LOADER,null,this);

        return rootView;
    }

    @Override
    public Loader<ArrayList<Categories>> onCreateLoader(int id, Bundle args) {
        return new FetchCategoriesLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Categories>> loader, final ArrayList<Categories> data) {
        final String[] categoriesArr = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            categoriesArr[i] = data.get(i).getTitle();
        }
        mAdapter.addAll(categoriesArr);


        mCategoriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),ArticlesActivity.class);
                intent.putExtra(ARTICLE_INTENT_EXTRA,data.get(position).getApiUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Categories>> loader) {
        mAdapter.clear();
    }
}
