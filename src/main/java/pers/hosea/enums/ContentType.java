package pers.hosea.enums;

public enum ContentType {
//    全MIME类型 https://www.iana.org/assignments/media-types/media-types.xhtml
//    text/plain：纯文本文件
//    text/html：HTML文件
//    text/css：CSS文件
//    text/javascript：JavaScript文件
//    application/json：JSON数据
//    application/xml：XML数据
//    image/jpeg：JPEG图片
//    image/png：PNG图片
//    audio/mpeg：MPEG文件
//    video/mp4：MP4视频
    NULL(null),
    TEXT_PLAIN("text/plain"),
    TEXT_HTML("text/html"),
    TEXT_CSS("text/css"),
    TEXT_JS("text/javascript"),
    TEXT_JAVASCRIPT("text/javascript"),
    APPLICATION_JSON("application/json"),
    APPLICATION_XML("application/xml"),
    IMAGE_JPEG("image/jpeg"),
    IMAGE_PNG("image/png"),
    AUDIO_MPEG("audio/mpeg"),
    VIDEO_MP4("video/mp4"),

    ;
    private final String content;

    ContentType(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return this.getContent();
    }
}
