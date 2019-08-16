package com.fanikiosoftware.mynews;

import com.fanikiosoftware.mynews.controllers.utility.Constants;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
class ConstantsTest {
    @Test
    public void titleIsCorrect() {
        String expected = "Top Stories";
        String title = Constants.titles[0];
        assertEquals(expected, title);
    }

    @Test
    public void apiIKeyIsCorrect() {
        String expected = "nHg4SGAl3zIrn5oT8ik9PQnhKXNsnjh6";
        String apiKey = Constants.API_KEY;
        assertEquals(expected, apiKey);
    }

    @Test
    public void baseImageURLIsCorrect() {
        String expected = "https://static01.ic_nyt.com/";
        String url = Constants.BASE_IMAGE_URL;
        assertEquals(expected, url);
    }

    @Test
    public void userQueryListIsCorrect() {
        String expected = "userQueryList";
        String userQueryList = Constants.USER_QUERY_LIST;
        assertEquals(expected, userQueryList);
    }
}