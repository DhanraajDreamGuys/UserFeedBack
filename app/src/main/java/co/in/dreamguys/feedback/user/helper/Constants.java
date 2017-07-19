package co.in.dreamguys.feedback.user.helper;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import co.in.dreamguys.feedback.user.ViewFeedbackList;
import co.in.dreamguys.feedback.user.response.FilterResponse;
import co.in.dreamguys.feedback.user.response.NotesViewListResponse;
import co.in.dreamguys.feedback.user.response.SurveyListResponse;

/**
 * Created by user5 on 12-07-2017.
 */

public class Constants {


    public static final String CATEGORYNAME = "color";
    public static String CATEGORYANSWER;
    public static String APP_NAME = "UserFeedBack";
    public static String RESTAURANT_ID = "restaurantId";
    public static JSONArray NotesList;
    public static List<NotesViewListResponse.Datum> ArraylistNotes = new ArrayList<>();
    public static List<SurveyListResponse.Datum> ArraySurveyList = new ArrayList<>();
    public static List<FilterResponse.Datum> ArrayFilter = new ArrayList<>();

    public static String NotesId;
    public static String CATEGORYID;
    public static String FILTERSKINTONE = "";
    public static int FILTERGENDER;
    public static String FILTERAGE = "";
    public static String FILTERDATE = "";
    public static ViewFeedbackList ViewFeedbackList;
}
