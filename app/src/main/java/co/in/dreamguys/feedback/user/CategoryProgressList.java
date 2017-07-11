package co.in.dreamguys.feedback.user;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import co.in.dreamguys.feedback.user.adapter.CategoryProgressListAdapter;
import co.in.dreamguys.feedback.user.model.DAOCategory;

/**
 * Created by Prasad on 7/11/2017.
 */

public class CategoryProgressList extends AppCompatActivity {

    ListView listCategories;
    CategoryProgressListAdapter aCategoryProgressListAdapter;
    List<DAOCategory> arrayCategoryLists = new ArrayList<DAOCategory>();
    DAOCategory mDAOCategory;
    String[] categoryName;
    int[] colorValue;
    String[] percentageValue;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_progress);

        intiWidgets();

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


        aCategoryProgressListAdapter = new CategoryProgressListAdapter(this, arrayCategoryLists);
        listCategories.setAdapter(aCategoryProgressListAdapter);


    }

    private void intiWidgets() {
        listCategories = (ListView) findViewById(R.id.list_categories);
    }
}