package com.example.lucer_000.redrider.Data;



import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class PostRepository {

    private static PostRepository INSTANCE = null;



    private Map<String, Post> mCachedPosts;


    public static PostRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PostRepository();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public void savePost(Post post) {
        System.out.println("\n\npost: " + post.getDestination());

        // Do in memory cache update to keep the app UI up to date
        if (mCachedPosts == null) {
            mCachedPosts = new LinkedHashMap<>();
        }
        mCachedPosts.put(post.toString(), post);
    }

    public ArrayList getPosts(){

        if (mCachedPosts != null ) {

            return new ArrayList(mCachedPosts.values());
        }
        return new ArrayList();
    }

}
