package co.in.dreamguys.feedback.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user5 on 18-07-2017.
 */

public class SkinToneResponse {


    public class Datum {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("skin_tone")
        @Expose
        private String skin_tone;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getSkin_tone() {
            return skin_tone;
        }

        public void setSkin_tone(String skin_tone) {
            this.skin_tone = skin_tone;
        }

    }

    public class UserSkinToneResponse {

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
