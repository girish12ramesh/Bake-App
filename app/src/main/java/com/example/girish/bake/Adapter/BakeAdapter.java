package com.example.girish.bake.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.girish.bake.Activity.MainActivity;
import com.example.girish.bake.Model.BakeResponse;
import com.example.girish.bake.R;

import java.util.List;


public class BakeAdapter extends RecyclerView.Adapter<BakeAdapter.MovieViewHolder> {
final String TAG="RecyclerView";
    private List<BakeResponse> bakeResults;
    private int rowLayout;
    private Context context;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout bakeLayout;
        TextView bakeName;
        TextView data;
        TextView movieDescription;
        TextView rating;


        public MovieViewHolder(View v) {
            super(v);
            bakeLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            bakeName = (TextView) v.findViewById(R.id.title);
            //data = (TextView) v.findViewById(R.id.subtitle);
            //movieDescription = (TextView) v.findViewById(R.id.description);
            //rating = (TextView) v.findViewById(R.id.rating);
        }
    }

    public BakeAdapter(List<BakeResponse> bakeResults, int rowLayout, Context context) {
        this.bakeResults = bakeResults;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public BakeAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.bakeName.setText(bakeResults.get(position).getName());
      //  holder.data.setText(bakeResults.get(position).getReleaseDate());
       // holder.movieDescription.setText(bakeResults.get(position).getOverview());
        //holder.rating.setText(bakeResults.get(position).getVoteAverage().toString());

        holder.bakeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
               /*
                mainActivity.callDetailPage(
                        "http://image.tmdb.org/t/p/w342/"+movieListDataModels.get(position).getImagePath(),
                        movieListDataModels.get(position).getUserRating(),
                        movieListDataModels.get(position).getMovieName(),
                        movieListDataModels.get(position).getDescription(),
                        movieListDataModels.get(position).getReleaseDate());
            */
            }
        });
    }

    @Override
    public int getItemCount() {
        return bakeResults.size();
    }
}