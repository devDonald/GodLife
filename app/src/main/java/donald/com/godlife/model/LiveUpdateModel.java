package donald.com.godlife.model;

public class LiveUpdateModel {

    private String post, postDate;

    public LiveUpdateModel() {
    }

    public LiveUpdateModel(String post, String postDate) {
        this.post = post;
        this.postDate = postDate;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
}
