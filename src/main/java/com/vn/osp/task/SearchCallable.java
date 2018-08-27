package com.vn.osp.task;

import com.vn.osp.common.global.Constants;
import com.vn.osp.modelview.PreventContractList;
import com.vn.osp.modelview.TransactionProperty;
import com.vn.osp.service.STPAPIUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

/**
 * Created by VIETDUNG on 23/05/2017.
 */
public class SearchCallable implements Callable {
    private PreventContractList data;
    private final Queue queue;

    public SearchCallable(PreventContractList data, final Queue queue) {
        this.data = data;
        this.queue = queue;
    }

    public PreventContractList call() throws Exception {
        PreventContractList result = null;

            String link = (String)queue.poll();
            result = STPAPIUtil.getPreventContractList(link, data.generateJson());



        //PreventContractList result = STPAPIUtil.getPreventContractList("http://localhost:8081/api"+"/search/transaction", data.generateJson());
        //PreventContractList result = STPAPIUtil.getPreventContractList(link, data.generateJson());
        return result;
    }
}
