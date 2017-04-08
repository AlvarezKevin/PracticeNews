package example.alvarezkevin.udacitynews.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import example.alvarezkevin.udacitynews.R;
import example.alvarezkevin.udacitynews.data.Article;
import example.alvarezkevin.udacitynews.ui.WebsiteActivity;

/**
 * Created by Kevin on 4/3/2017.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.View_Holder> {

    private Context mContext;
    protected ArrayList<Article> articlesList;

    public ArticlesAdapter(ArrayList<Article> articles, Context context) {
        articlesList = articles;
        mContext = context;
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_item_articles,parent,false);
        View_Holder viewHolder= new View_Holder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(View_Holder holder, int position) {
        holder.setPos(position);
        holder.titleView.setText(articlesList.get(position).getTitle());
        holder.dateview.setText(articlesList.get(position).getWebPublicationDate().substring(0,10));
        holder.sectionView.setText(articlesList.get(position).getSectionName());

    }


    @Override
    public int getItemCount() {
       if(articlesList != null) {
           return articlesList.size();
       }
       return 0;
    }

    public void clear() {
        articlesList.clear();
        notifyDataSetChanged();
    }

    public void addList(ArrayList<Article> articles) {
        articlesList = articles;
        notifyDataSetChanged();
    }

    public class View_Holder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView titleView;
        TextView dateview;
        TextView sectionView;
        private int mPosition;

        private static final String ARTICLE_INTENT_URL_EXTRA = "ARTICLE_URL";
        private static final String ARTICLE_INTENT_TITLE_EXTRA = "ARTICLE_TITLE";

        public View_Holder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.list_item_cardview);
            titleView = (TextView)itemView.findViewById(R.id.list_item_title);
            dateview = (TextView)itemView.findViewById(R.id.list_item_date);
            sectionView = (TextView)itemView.findViewById(R.id.list_item_section);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, WebsiteActivity.class);
                    intent.putExtra(ARTICLE_INTENT_URL_EXTRA,articlesList.get(mPosition).getUrl());
                    intent.putExtra(ARTICLE_INTENT_TITLE_EXTRA,articlesList.get(mPosition).getTitle());
                    mContext.startActivity(intent);
                }
            });
        }
        public void setPos(int pos) {
            mPosition = pos;
        }
    }
}
