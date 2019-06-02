package io.rong.imlib.filetransfer;

public class FtConst {

    public enum MimeType {
        NONE(0, "none"),
        FILE_IMAGE(1, "image_jpeg"),
        FILE_AUDIO(2, "audio_amr"),
        FILE_VEDIO(3, "video_3gpp"),
        FILE_TEXT_PLAIN(4, "text_plain");
        
        private String name;
        private int value;

        private MimeType(int i, String str) {
            this.value = 1;
            this.name = "";
            this.value = i;
            this.name = str;
        }

        public int getValue() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }

        public static MimeType setValue(int i) {
            for (MimeType mimeType : values()) {
                if (i == mimeType.getValue()) {
                    return mimeType;
                }
            }
            return NONE;
        }
    }

    public enum ServiceType {
        QI_NIU
    }
}
