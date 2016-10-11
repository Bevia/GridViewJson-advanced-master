package com.corebaseit.advancedgridviewjson.resttasks;

import java.net.URL;

/**
 * Created by vbevia on 31/08/16.
 */
public class ListFromJsonUrlTask extends RESTAbstractTask {

    public ListFromJsonUrlTask(URL url) {
        super();
        this.setUrl(url);
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (this.callable != null && this.callable instanceof Callable) {
            ((Callable) this.callable).showResult(this);
        }
    }

    public interface Callable {
        void showResult(ListFromJsonUrlTask task);

    }
}

