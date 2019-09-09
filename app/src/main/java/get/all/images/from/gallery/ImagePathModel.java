package get.all.images.from.gallery;

public class ImagePathModel {

    public ImagePathModel(String absoluteImagePath) {
        this.absoluteImagePath = absoluteImagePath;
    }

    public String getAbsoluteImagePath() {
        return absoluteImagePath;
    }

    public void setAbsoluteImagePath(String absoluteImagePath) {
        this.absoluteImagePath = absoluteImagePath;
    }

    private String absoluteImagePath;

}
