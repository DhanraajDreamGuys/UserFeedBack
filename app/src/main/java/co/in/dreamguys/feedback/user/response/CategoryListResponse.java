package co.in.dreamguys.feedback.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user5 on 11-07-2017.
 */

public class CategoryListResponse {


    public class Datum {

        @SerializedName("category_id")
        @Expose
        private String category_id;
        @SerializedName("category_name")
        @Expose
        private String category_name;

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String notes_id) {
            this.category_id = notes_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

    }

    public class UserCategoryListResponse {

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
