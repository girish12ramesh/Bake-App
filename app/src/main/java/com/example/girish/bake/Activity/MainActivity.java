package com.example.girish.bake.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.girish.bake.Adapter.BakeAdapter;
import com.example.girish.bake.Model.BakeResponse;
import com.example.girish.bake.R;
import com.example.girish.bake.Rest.ApiClient;
import com.example.girish.bake.Rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    List<BakeResponse> mListDataModels = new ArrayList<BakeResponse>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // API :   https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json

        // Recycler View
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<BakeResponse>> call = apiService.getResponse();

        call.enqueue(new Callback<List<BakeResponse>>() {
            @Override
            public void onResponse(Call<List<BakeResponse>> call, Response<List<BakeResponse>>response) {
                Log.d(TAG,"Succesfull Network call from Bake");
               // Log.d(TAG,"Network Call  "+response.body().get));
                Toast.makeText(MainActivity.this,"Sucessful Network", Toast.LENGTH_LONG).show();
                if(response.isSuccessful()){
                    if(response.body().size() != 0 && ! response.body().isEmpty()){
                        for(int i=0;i<response.body().size();i++){
                            mListDataModels.add(new BakeResponse(response.body().get(i).getId(),
                                    response.body().get(i).getName(),response.body().get(i).getIngredients(),
                                    response.body().get(i).getSteps(),response.body().get(i).getServings()));
                            Log.d(TAG,mListDataModels.get(i).getName()+mListDataModels.get(i).getId());
                        }
                    }
                }

                recyclerView.setAdapter(new BakeAdapter(mListDataModels,MainActivity.this, R.layout.main_list, getApplicationContext()));

            }
            @Override
            public void onFailure(Call<List<BakeResponse>>call, Throwable t) {
                //Log.d(TAG,"Network Failure call from Bake");
                Log.e(TAG,"Network Call Failure "+t.getMessage(),t);
                Toast.makeText(MainActivity.this,"Network Failure ", Toast.LENGTH_LONG).show();
            }
        });
    }
//mainActivity.callDetailPage(
                      //  bakeResults.get(position).getIngredients(),
                      //  bakeResults.get(position).getSteps(),
                      //  bakeResults.get(position).getName(),
                      //  bakeResults.get(position).getId());

    public void callDetailPage(){

            Intent i = new Intent(this,Main2Activity.class);

            startActivity(i);

    }

}
