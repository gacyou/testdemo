package test.com.testdemo;

import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;

import java.util.List;

import test.com.testdemo.Adapter.GridViewAdapter;
import test.com.testdemo.Api.ApiLoaderCallback;
import test.com.testdemo.Api.Base.ApiResult;
import test.com.testdemo.Bean.ApiData;
import test.com.testdemo.Loader.GetApiDataLoader;

public class ApiActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridViewAdapter mGridViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        ApiLoaderCallback.startApiLoader(this, ApiDataLoaderCallback);
    }

    ApiLoaderCallback<List<ApiData>> ApiDataLoaderCallback = new ApiLoaderCallback<List<ApiData>>() {
        @Override
        public Loader<ApiResult<List<ApiData>>> onCreateLoader(int id, Bundle args) {
            return new GetApiDataLoader(ApiActivity.this);
        }

        @Override
        protected void onStatusOk(List<ApiData> data) {
            mGridViewAdapter = new GridViewAdapter(data);
            recyclerView.setAdapter(mGridViewAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(ApiActivity.this,4));
        }
    };
}
