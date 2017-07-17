package co.in.dreamguys.feedback.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user5 on 17-07-2017.
 */

public class NotesViewListResponse {


    public class Datum {

        @SerializedName("notes_id")
        @Expose
        private String notes_id;
        @SerializedName("notes_cat_id")
        @Expose
        private String notes_cat_id;
        @SerializedName("res_id")
        @Expose
        private String res_id;
        @SerializedName("notes")
        @Expose
        private String notes;
        @SerializedName("notes_date")
        @Expose
        private String notes_date;
        @SerializedName("created_date")
        @Expose
        private String created_date;
        @SerializedName("delete_flag")
        @Expose
        private String delete_flag;
        @SerializedName("category_name")
        @Expose
        private String category_name;

        public String getNotes_id() {
            return notes_id;
        }

        public void setNotes_id(String notes_id) {
            this.notes_id = notes_id;
        }

        public String getNotes_cat_id() {
            return notes_cat_id;
        }

        public void setNotes_cat_id(String notes_cat_id) {
            this.notes_cat_id = notes_cat_id;
        }

        public String getRes_id() {
            return res_id;
        }

        public void setRes_id(String res_id) {
            this.res_id = res_id;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public String getNotes_date() {
            return notes_date;
        }

        public void setNotes_date(String notes_date) {
            this.notes_date = notes_date;
        }

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
        }

        public String getDelete_flag() {
            return delete_flag;
        }

        public void setDelete_flag(String delete_flag) {
            this.delete_flag = delete_flag;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }
    }

    public class UserNotesViewListResponse {

        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("meta")
        @Expose
        private String meta;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("data")
        @Expose
        private List<Datum> data = null;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMeta() {
            return meta;
        }

        public void setMeta(String meta) {
            this.meta = meta;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<Datum> getData() {
            return data;
        }

        public void setData(List<Datum> data) {
            this.data = data;
        }

    }

}
