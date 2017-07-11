package co.in.dreamguys.feedback.user;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import co.in.dreamguys.feedback.user.adapter.CategoryProgressListAdapter;
import co.in.dreamguys.feedback.user.model.DAOCategory;
import co.in.dreamguys.feedback.user.network.CategoryProgressListAPI;
import co.in.dreamguys.feedback.user.response.CategoryListResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Prasad on 7/11/2017.
 */

public class CategoryProgressList extends AppCompatActivity {

    ListView listCategories;
    CategoryProgressListAdapter aCategoryProgressListAdapter;
    List<DAOCategory> arrayCategoryLists = new ArrayList<DAOCategory>();
    DAOCategory mDAOCategory;
    List<CategoryListResponse.Datum> arrayCategoryList = new ArrayList<>();
    String[] categoryName;
    int[] colorValue;
    String[] percentageValue;
    String restaurant_id = "1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_progress);
        intiWidgets();
        CategoryProgressListAPI.getInstance().Callresponse(restaurant_id, new Callback<CategoryListResponse.UserCategoryListResponse>() {
            @Override
            public void success(CategoryListResponse.UserCategoryListResponse userCategoryListResponse, Response response) {
                if (userCategoryListResponse.getStatus().equalsIgnoreCase("Y")) {
                    arrayCategoryList = userCategoryListResponse.getData();
                    if (arrayCategoryList.size() > 0) {
                        aCategoryProgressListAdapter = new CategoryProgressListAdapter(CategoryProgressList.this, arrayCategoryList);
                        listCategories.setAdapter(aCategoryProgressListAdapter);
                    } else {
                        Log.i("TAG", "empty list");
                    }
                    Log.i("TAG", arrayCategoryList.toString());
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


        categoryName = new String[]{
                "Great",
                "Spicy",
                "Salty",
                "No taste"
        };

        colorValue = new int[]{
                Color.BLUE,
                Color.RED,
                Color.YELLOW,
                Color.GRAY,
        };

        percentageValue = new String[]{
                "100",
                "82",
                "54",
                "20"
        };

        for (int i = 0; i < categoryName.length; i++) {
            mDAOCategory = new DAOCategory();
            mDAOCategory.setCategoryName(categoryName[i]);
            mDAOCategory.setIntColorValue("" + colorValue[i]);
            mDAOCategory.setIntPercentageValue(percentageValue[i]);
            arrayCategoryLists.add(mDAOCategory);
        }


    }

    private void intiWidgets() {
        listCategories = (ListView) findViewById(R.id.list_categories);
    }


    private void ProgressList() {

    }

}
