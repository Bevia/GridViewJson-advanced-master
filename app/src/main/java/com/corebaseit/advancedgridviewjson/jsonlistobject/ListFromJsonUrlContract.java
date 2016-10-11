package com.corebaseit.advancedgridviewjson.jsonlistobject;


import com.corebaseit.advancedgridviewjson.models.ListFromJsonUrlModel;

import java.util.Vector;

/**
 * Created by vincentbevia on 06/10/16.
 */

public interface ListFromJsonUrlContract {

    interface ListFromJsonUrlContractView{

        void showList(Vector<ListFromJsonUrlModel> models);

    }
}
